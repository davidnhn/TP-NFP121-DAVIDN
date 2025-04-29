@echo off
REM Nettoyer les fichiers .class existants
del /Q allumettes\*.class

REM Compiler toutes les classes du projet
javac -cp .;lib\junit4.jar;lib\hamcrest-core.jar allumettes\*.java

REM Exécuter le test pour StrategieRapide
java -cp .;lib\junit4.jar;lib\hamcrest-core.jar org.junit.runner.JUnitCore allumettes.StrategieRapideTest

pause 