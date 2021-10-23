Author : Meraman Odedra

User Account Registration APIs allow to create user, list all users, get perticular user, update monthly salary/expense, delete users
Once user is created, user can apply for a new account, list all accounts



     Steps: 
     1. Get code from git repository 'https://github.com/mrodedra/UserAccountRegistration.git'

     2. Database Creation:
        There are 2 tables, User and Account. User can have multiple account so there is One-To-Many relationship between User and Account
        Database : MySQL
        Scripts: i. execute User creation script from resources/scripts/user_ddl.sql
                 ii. execute Account creation script from resources/scripts/account_ddl.sql
               
                 Alternatively to create schemas automatically, change the application.properties to spring.jpa.hibernate.ddl-auto=create


     3.Once repository is cloned, go to 'UserAccountRegistration' from command prompt/alternatively you can use any IDE. For simplicity I haven't created any docker image.

     4.Build and install project using maven command 'mvn clean install'

     5.Run Spring Boot Application using maven command 'mvn spring-boot:run'
       Please note that for simplicity, I havn't used any profile otherwise we need to run application with respective profile like dev, uat, prod etc..
     
     6. Test application using postman or curl command
        APIs:
        i. Create User:
           URI: http://localhost:8080/api/users
           HTTP method: POST
           Request Body:JSON format
                     {
                      "name":"user1",
                      "email":"user1@gmail.com",
                      "monthlySalary":4500.00,
                      "monthlyExpense":3000.00
                     }
       ii. List Users:
           URI: http://localhost:8080/api/users
           HTTP method: GET
       iii.Get User:
           URI: http://localhost:8080/api/users/{userId}
           HTTP method: GET
       iv.Update User:
           URI: http://localhost:8080/api/users/{userId}
           HTTP method: PUT
           Request Body: JSON format
                   {
    			"monthlySalary":4500.00,
    			"monthlyExpense":3000.00
		   }
      v.Delete User:
           URI: http://localhost:8080/api/users/{userId}               //Warning:It will delete user with all associated accounts
           HTTP method: DELETE
      vi. Create Account:
           URI: http://localhost:8080/api/users/{userId}/accounts
           HTTP method: POST
           Request Body:JSON format
                     {
                        "accountType":"credit card"
                     }
      vii. List Accounts:
           URI: http://localhost:8080/api/users/{userId}/accounts
           HTTP method: GET
                 
        