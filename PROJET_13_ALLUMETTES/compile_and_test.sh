#!/bin/bash

# Nettoyer les fichiers .class existants
rm -f allumettes/*.class

# Compiler toutes les classes du projet
javac -cp ".;lib/junit4.jar;lib/hamcrest-core.jar" allumettes/*.java

# Exécuter le test pour StrategieRapide
java -cp ".;lib/junit4.jar;lib/hamcrest-core.jar" org.junit.runner.JUnitCore allumettes.StrategieRapideTest 