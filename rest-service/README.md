# Starting the Project
- to start no arguments are needed

# postman tasks
- the postman collection can be found in frameworksAndDrivers/postman/
- It should be noted that delete order item and delte order can only pass the test the first time they are executed, as they delete a predefined item and a predefined order from the database, which will no longer be there the next time they are executed.


# Argumentation for persistence-oriented repository
- The persistence-oriented approach has the advantage that the code is easier to extend. This makes it easier to switch to a persistent storage solution, such as a database
- The collection-oriented approach is closely linked to the specific storage structure. This restricts flexibility. The persistence-oriented approach has the advantage that it completely abstracts data persistence, which means that the application does not have to worry about the details of data storage.
- The persistence-oriented approach is more flexible, as this form of interface supports both an in-memory and a database-based implementation.
