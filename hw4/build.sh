#!/usr/bin/env bash
sudo apt-get install openjdk-8-jdk
./gradlew distTar
tar -xf build/distributions/hw4/hw4-1.0-SNAPSHOT.tar
