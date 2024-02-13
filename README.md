# National Blood Donation Registry

This repository houses the backend component of a government website designed to facilitate easier access for citizens who wish to donate. The platform aims to streamline the donation process and enhance accessibility for citizens interested in contributing.

## Using Api
You need the following prerequisities

[Java Development Kit (JDK) 8 or higher](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)

[Maven](https://maven.apache.org/download.cgi)

[Postgres Database](https://www.postgresql.org/download/)(We recommend using postgresql)

## Running the SpringBootApplication

All additional dependencies to run the application exist inside the pom.xml file

---
**NOTE**

The commands in this section should be executed as the `root` user in the git bash

```
git clone https://github.com/rafaelasterg/BloodDonation.git
cd BloodDonation
mvn clean install
```
## Configuration

This repository is mainly tested and developed with [IntellijIdea](https://www.jetbrains.com/idea/download/?section=windows) , but you can run it on any other idea.

---
If you dont use an idea , you can simply run it using the command line.
Firstly, built project by running the command mvn clean install.This will create a jar file inside the directory `target` and by running this command you will execute it.

```
java -jar BloodDonation.jar
```


