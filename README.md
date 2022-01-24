# REST API - Grade Registration Management System
> Grade registration management system, where it is possible to register specific grades of specific students.

> Before registering the test grade, it is necessary to register the student responsible for that grade

- Maven

- Spring Boot

- Sprint Data JPA

- Spring Security e JWT

- Bean Validation

- Lombok

- Swagger

in development...


## StudentController

### Request http://locacalhost:8080/api/student

`POST - http://localhost:8080/api/student/save`
#### Body

    {
    "nome" : "gabriel",
    "dataNascimento" : "2000-01-31"
    }

#### Response

    {
    "id": 2,
    "nome": "gabriel",
    "dataNascimento": "2000-01-31"
    }

    Status : 201 Created
    
>---------------------------------------------

`GET - http://localhost:8080/api/student/1`
#### Response

    {
    "id": 1,
    "nome": "gabriel",
    "dataNascimento": "2000-01-01"
    }

    Status : 200 OK
    
>---------------------------------------------

`PUT - http://localhost:8080/api/student/1`
#### Body

    {
    "nome" : "Rafael",
    "dataNascimento" : "2004-04-26"
    }

#### Response
    
    Status : 200 OK
    
>---------------------------------------------

`DELETE - http://localhost:8080/api/student/1`

#### Response
    
    Status : 204 No Content
    
>---------------------------------------------

`GET - http://localhost:8080/api/student?nome=ga`
`TO GET ALL STUDENTES = Don't use parameters
 TO GET SPECIFIC STUDENTS = Use parameters`

#### Response

    [
    {
        "id": 1,
        "nome": "gabriel",
        "dataNascimento": "2021-01-31"
    }
    ]

    Status : 200 OK
    
## GradeController - Request: http://locacalhost:8080/api/grade

### to be implemented
