#!/bin/bash
set -e

echo "Building restaurant-search-engine docker image"
./gradlew clean build
sudo docker build -t br.com/restaurant-search-engine:0.1.0 -f Dockerfile .
echo "Built restaurant-search-engine docker image"

echo "Running docker-compose up..."
sudo docker-compose -f docker-compose.yml up -d
echo "Done docker-compose up..."

echo "Cleaning previous docker images..."
sudo docker image prune -f
sudo docker container prune -f
echo "Cleaned previous docker images..."