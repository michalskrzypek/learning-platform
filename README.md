# Learning Platform
Single Page Application (SPA) based on Spring framework (Boot, Data, Security) + Angular 6 + PostgreSQL. For authentication purposes JSON WEB TOKEN authentication was fully implemented.

## What's in the code?
1.	Java
•	Spring Boot
•	Spring Data
•	Spring Security (with authentication based on JWT)
•	Spring Mail
•	Custom method annotations 
•	Customized Exception Handler (together with custom exceptions)
•	OOP Design Patterns (e.g. Observer, Factory, Builder  -> Check MailTemplateFactory implementation)
•	Clean code principles
•	Optionals
•	Streams
•	Functional interfaces
•	YAML-based properties

2.	Angular 6
•	HTTP Services
•	Two-way, One-way data bindings
•	Toasters plugin
•	Twitter lookalike tag input plugin
•	Custom directives
•	Bootstrap 4 for views

## How does it look like?
### Log in page: To have access to any content the user must be logged in (JWT token-based authentication takes responsibility for authenticate and authorize the user)
![alt text](https://i.imgur.com/b6HOZVe.png)

### Home page: from now on (as an admin user) we have access to each content. Content is hidden based on the user role. Spring security provide us with security on the server side. 
![alt text](https://i.imgur.com/z1r3xxx.png)

### Adding new course: only admin and instructors have the possibility to add new courses. 
![alt text](https://i.imgur.com/8C77Jf3.png)

### Once created, we can see the course in the list of all courses AND a mail with confirmation is sent to the course creator.
![alt text](https://i.imgur.com/akQrYxc.png)
![alt text](https://i.imgur.com/QVMzm2c.png)

Users can add courses to their library.

### Admin can also manage all the courses (edit or delete them)
![alt text](https://i.imgur.com/CIgR14u.png)

## How can you use the application?
For right now, you can only download the source code, change the PostgreSQL database credentials in properties and run the application.
Then you must run the Angular application via Angular CLI (just provide 'ng serve' in the bash). And that is it! Your application works on the localhost at the 4200 port.


