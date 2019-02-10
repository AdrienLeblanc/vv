# Validation et Vérification

Erwan IQUEL - Adrien LEBLANC

## Pré-requis

* [Java 8 or newer](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/) installé de manière global
* [Docker](https://docs.docker.com/install/)
* [Git](https://git-scm.com/book/fr/v1/D%C3%A9marrage-rapide-Installation-de-Git)
* [Jenkins](https://hub.docker.com/r/jenkinsci/blueocean/)

## Quickstart

Pour lancer le jeu de test:

```bash
$ mvn test 
```

Pour installer le projet localement:

```bash
$ mvn clean install -DskipTests
```

Pour lancer le programme    

```bash
$ java -jar assertion-generation-processor/target/assertion-generation-processor.jar /path/vers/le/projet/maven nom_de_la_méthode
```

## Test

Project tested on:

* nothing