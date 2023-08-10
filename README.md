# Technical Task : library system

A Spring boot Rest API with following features has implemented.

### Books API features:
- Add new books `http://localhost:8081/books/save`
- List all available books `http://localhost:8081/books/list`
- Search books by title `http://localhost:8081/books/filterbybook/{title}`
- Search books by title `http://localhost:8081/books/filterbyauthor/{author}`

### Author API features:
- Add new Author `http://localhost:8081/authors/save`
- List all authors `http://localhost:8081/authors/filter/{name}`

### Loan API features:
- Enter loan or return book details, borrowed date is mandatory `http://localhost:8081/loans/save`
- List all loan records `http://localhost:8081/loans/list`
- All loans for a user `http://localhost:8081/loans/filterbyuser/{userid}`
- Currently active/pending loans for a user `http://localhost:8081/loans/filteractiveuserloan/{userid}`
- Currently active/pending all loans details `http://localhost:8081/loans/filteractiveloan`

### User API features:
- Create new user `http://localhost:8081/users/save` 
- View All users `http://localhost:8081/users/list`

### Authentication and Authorization API With JWT:
- Authenticate user `http://localhost:8081/authenticate`
- Authorize above Books, Author and Loan APIs with Authority levels of "ADMIN", "STAFF" and "MEMBER"

### Web Socket API features:
#### URL: ws://localhost:8081/websocket
- Simple web socket connects through Postman and display response
- When new book is saved, display message "New Book Arrived: {Name} By Author: {Name} : Available Count:{Number}"
- When a book returned, display message "Returned Book: {Name} By Author: {Name}: Available Count:{Number}"

### Documentation
- API documentation available in `http://localhost:8081/swagger-ui/index.html?configUrl=/api-docs/swagger-config#/` and JSON format in `http://localhost:8081/api-docs`


## Implementation
- Used Spring boot REST API for API development, may start with new user creation.
- Used h2 in memory database
- All the API services, Security service and Web socket service included in same app service
- For security, used JWT authentication service with prepost enabled authorities
- Web socket implemented server side only and call it when book save and return. The relevant messages can be viewed on Postman by connecting as Web socket


### Future enhancements
- Enable microservice by strangulation modules like Auth, WebSocket and loan API service
- add more features like include ebooks, videos, audio records and categories with patterns
- Improve System validation and provide proper error codes for each case
