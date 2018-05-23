# oppgave9

## Installation

* Clone the project
* Add Firebase key
* Build and run jar

### Clone the project
`git clone https://github.com/nubk/oppgave9.git`

### Add firebase key
The system requires a Firebase project, and credentials for that project stored in a file: `src/main/resources/deploy/serviceAccountKey.json`. This key can be downloaded from your Firebase project ([instructions](https://firebase.google.com/docs/admin/setup)).

### Build and run jar
Once you have added the Firebase credentials, you can build the system. To build a jar, navigate to the project root folder (oppgave9) and type the following commands: 
* `mvn install` (this will create a jar with all the required dependencies in the target folder)
* `cd target` (navigate to target folder)
* `java -jar oppgave9-1.0-SNAPSHOT-jar-with-dependencies.jar` (this will run the application)

## Populate database with fake data
It can be useful to have some fake data for development purposes. You can run `src/main/java/app/util/FakeDataUtil.java` to populate the database. Please note that this will delete any real data.
