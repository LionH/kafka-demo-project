# Kafka Demo Project

This repository demonstrates a simple Kafka setup with a producer and consumer application, both written in Kotlin with Spring Boot. The project uses Kubernetes for deployment with Helm charts.

## Project Structure

```
├── producer/                # Producer module
├── consumer/                # Consumer module
├── helm/                    # Helm chart for deployment
│   ├── kafka-demo-chart/
│   │   ├── Chart.yaml       # Helm chart metadata
│   │   ├── values.yaml      # Configurable values for the chart
│   │   └── templates/       # Kubernetes manifests
├── build.gradle.kts         # Gradle build script
├── settings.gradle.kts      # Gradle settings
├── .gitignore               # Git ignore file
└── README.md                # Project documentation
```

## Prerequisites

1. **Docker**
2. **Docker Desktop with Kubernetes enabled**
3. **Helm**
4. **kubectl**
5. **Gradle**
6. **Java 21 (Amazon Corretto)**

## Setup and Deployment

### Step 1: Build the Project

Build the producer and consumer boot JARs:
```bash
./gradlew :producer:bootJar
./gradlew :consumer:bootJar
```

### Step 2: Build Docker Images

Build Docker images for the producer and consumer:
```bash
docker build -t kafka-producer:latest ./producer
docker build -t kafka-consumer:latest ./consumer
```

### Step 3: Deploy with Helm

Use Helm to deploy Kafka, producer, and consumer:
```bash
helm install kafka-demo ./helm/kafka-demo-chart
```

### Step 4: Verify the Deployment

Check the status of the pods:
```bash
kubectl get pods
```

Ensure all pods (Kafka, producer, and consumer) are running.

### Step 5: Check Logs

Verify the producer and consumer logs to confirm message production and consumption:
```bash
kubectl logs <producer-pod-name>
kubectl logs <consumer-pod-name>
```

## Testing and Scaling

### Automatic Topic Creation
Ensure Kafka is configured to automatically create topics by setting:
```yaml
KAFKA_CFG_AUTO_CREATE_TOPICS_ENABLE: "true"
```

### Scaling the Consumer
Scale the consumer to handle more messages:
```bash
kubectl scale deployment consumer --replicas=3
```

## Cleanup

To delete the Helm deployment:
```bash
helm uninstall kafka-demo
```

## .gitignore

```
# Gradle build files
.gradle/
build/

# IntelliJ IDEA project files
*.iml
.idea/
out/

# Kotlin/Java build files
*.class
*.jar
*.war
*.ear

# Logs
*.log
logs/

# Temporary files
*.tmp
*.temp
*.swp

# OS-specific files
.DS_Store
Thumbs.db

# Docker-related files
*.env
*.dockerfile
docker-compose.override.yml

# Kubernetes and Helm
helm/kafka-demo-chart/charts/
helm/kafka-demo-chart/values.yaml.override
*.tgz

# Exclude specific module build directories
producer/build/
consumer/build/

# Ignore local test reports
reports/
test-results/
```

## Troubleshooting

### Common Errors

1. **`INVALID_REPLICATION_FACTOR`**:
   Ensure the topic is created with a replication factor of 1 for a single-node Kafka setup.

   ```bash
   kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic demo-topic
   ```

2. **`java.net.UnknownHostException`**:
   Ensure Kafka's advertised listeners are set correctly:
   ```yaml
   KAFKA_CFG_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092
   ```

## License
This project is licensed under the MIT License.

