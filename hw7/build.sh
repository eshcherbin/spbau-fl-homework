#!/bin/bash
sudo apt-get install openjdk-8-jdk
./gradlew distTar
tar -xf build/distributions/hw7-1.0-SNAPSHOT.tar
ln -sf hw7-1.0-SNAPSHOT/bin/hw7
