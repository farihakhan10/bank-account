# bank-account

Services are deployed on Heroku server. They can be accessed and tested on https://bank-account-service.herokuapp.com/swagger-ui.html

In memory H2 database can't be accessed on Heroku server.


**For Local Deployment**

**Pre requisites:**
Install JDK 11 on your machine

**Steps:**
- Take a pull from the main branch
- Run BankAccountApplication...main()
- Once the application is started on tomcat, you can login the H2 database
- Go to http://localhost:8080/h2-console and connect using following credentials:
  - JDBC URL: jdbc:h2:mem:testdb
  - username: sa
- There, you can see three tables: ACCOUNTS, CUSTOMERS, TRANSACTIONS
- You can access the APIs documentation on http://localhost:8080/swagger-ui.html
- There are two controllers: accounts-controller, customer-controller
- Test the API for creating current account for existing customer
  - In accounts-controller, you can test the the **createCurrentAccountByCustomerId** API by providing customerId and initialCredit. Note: Customer with customerId=1 is created by default for testing purpose.
- Test the API to get customer details
  - In customer-controller, you can test the **createCurrentAccountByCustomerId** API by providing customerId
- You can check the data populated in the H2 database at runtime
