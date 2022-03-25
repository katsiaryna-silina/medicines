# XML. DOM, SAX, StAX Parsers + XSD

## Medicines project

This is the second project of EPAM Java Web Development training.

Application with different XML files and their parsing technologies.

## Task description:

Create XML file that stores information about medicines. Develop XSD schema to validate this file. Parse xml document
using DOM, SAX, StAX parsers. Use JAXB technology.

## Requirements:

- required & optional for attributes
- enum
- patterns and limit values
- ID type
- default attribute values
- type extension (imitation of inheritance)
- date-time. In Java, use only the java.time package
- pattern Builder
- Log4J2
- code should be covered by tests

## Setup

+ Install Java SE Development Kit 11
+ Install Maven
+ Build the project with maven with the following command :

```
mvn clean install
```

+ Start app

```
java -jar .\target\medicines-1.0-SNAPSHOT.jar
```