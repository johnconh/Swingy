#!/bin/bash

# Limpiar el proyecto
mvn clean

# Compilar el proyecto y empaquetarlo en un .jar
mvn package

# Ejecutar el archivo .jar (ajusta la ruta del archivo .jar si es necesario)
java -jar target/RPGgame-1.0-SNAPSHOT.jar