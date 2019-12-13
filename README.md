# spring-rest-reactjs-demo

spring rest based backend project.
Note. for ease of testing this project also contains all html, js and css file (minified) of the reactjs SPA.
These files were genetared from the reactjs project which exists onthe following repository (https://github.com/muhammadwasay/reactjs-demo )
Access the restjs SPA from spring backend application by visiting following path @RequestMapping(value = "/").


Technology stack used
- spring boot
- spring web
- spring data jpa
- spring hatoas
- h2 database

Requirement Spectification Implemented

Employee
- List @GetMapping("/employees")
- View @GetMapping("/employees/{id}")
- Create @PostMapping("/employees")
- Edit @PutMapping("/employees/{id}")
- Delete @DeleteMapping("/employees/{id}")
- validation (first name, last name, salary, phone no)

Department
- List @GetMapping("/departments")
- View @GetMapping("/departments/{id}")
- Create @PostMapping("/departments")
- Edit @PutMapping("/departments/{id}")
- Delete @DeleteMapping("/departments/{id}")

Note: These all api are functional and can be testes form any rest client. Due to time availablity on my end the reactjs SAP only uses the list apis.
