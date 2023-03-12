##HOW TO BUILD AND RUN THE ROBOT-APOCALYPSE BACKEND SERVICE
* The application was built using springboot ver 2.7.9
* You'll need to have java (minimum of version 8) and maven installed to run the application.
* Build the application by running 'mvn clean package'
* Unit tests have also been included test dir.
* After that, run the application with 'mvn spring-boot:run'
* The application uses an embedded DB (for simplicity), so there'll be no need to configure any databases.
* Once the application is run, visit http://localhost:8080/swagger-ui.html
  This will give you access to the entire swagger documentation of the api.
  
  You can also test the api's via the same swagger url. Or otherwise you could use postman.