# healthmeal
A monorepo for all applications that will ensure that we are eating well

## Execution
To run all 3 applications and database, just run the command: `docker-compose up -d`

The database is hosted on `localhost:5432` with database `healthmeal`, user `postgres` and password `pass`.<p>
The `Users` application is running on port 9090.<p>
The `Products` application is running on port 9091.<p>
The `Orders` application is running on port 9092.

There is also a Postman collection file in the project, `Health Meal.postman_collection.json` with some of the requests.
