To test database connection, you need to create a file named "config.properties"
In this file, you write :
    db.url=jdbc:postgresql://localhost:5432/db_name
    db.user=user
    db.password=password

For the test :
Copy/paste all lines in database.sql and inserts.sql

ENDPOINTS:
    GET/transactions?type=in|out
    GET/accounts/{id}/transactions
    POST/transactions
    GET/account/{id}/balance