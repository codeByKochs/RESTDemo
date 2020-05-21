# Rest Demo

This is a demo project showing a java backend based on the spring framework.
The frontend is realized by a simple react app.

The address entry database is implemented by a db.json file. 
The server backend is running on "http://localhost:8080" and allows the following Http Requests
- GET
- Post
- DELETE
- PUT

## Getting Started
Put the db.json file in the folder of the .jar file. 
Run the .jar file in the commandline with the following command

>java -jar <{path-to-jar-file}>

Alternatively the path to the database can be set via

>java -jar <{path-to-jar-file}> --database.path=<{path-to-json-file}>

