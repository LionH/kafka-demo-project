#!/bin/bash

set -e  # Exit immediately if a command exits with a non-zero status

# Define variables
PRODUCER_IMAGE="kafka-producer:latest"
CONSUMER_IMAGE="kafka-consumer:latest"
PRODUCER_DEPLOYMENT="producer"
CONSUMER_DEPLOYMENT="consumer"

echo "Starting build and refresh process..."

# Step 1: Build bootJars
echo "Building bootJARs..."
./gradlew :producer:bootJar
./gradlew :consumer:bootJar
echo "BootJARs built successfully."

# Step 2: Build Docker images
echo "Building Docker images..."
docker build -t $PRODUCER_IMAGE ./producer
docker build -t $CONSUMER_IMAGE ./consumer
echo "Docker images built successfully."

# Step 3: Push Docker images (optional, uncomment if needed)
# echo "Pushing Docker images..."
# docker push $PRODUCER_IMAGE
# docker push $CONSUMER_IMAGE
# echo "Docker images pushed successfully."

# Step 4: Rollout restart deployments
echo "Restarting Kubernetes deployments..."
kubectl rollout restart deployment $PRODUCER_DEPLOYMENT
kubectl rollout restart deployment $CONSUMER_DEPLOYMENT
echo "Kubernetes deployments restarted successfully."

echo "Process complete!"

