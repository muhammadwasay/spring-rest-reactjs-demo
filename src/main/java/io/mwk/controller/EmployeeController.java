package io.mwk.controller;

import io.mwk.entity.Employee;
import io.mwk.exception.EmployeeNotFoundException;
import io.mwk.repository.EmployeeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class EmployeeController {

    private final EmployeeRepository repository;

    private final EmployeeResourceAssembler assembler;

    EmployeeController(EmployeeRepository repository, EmployeeResourceAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    @CrossOrigin
    @GetMapping("/employees")
    ResponseEntity<?> employees() {

        List<EntityModel<Employee>> employees = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

        CollectionModel<EntityModel<Employee>> entityModels = new CollectionModel<>(employees, linkTo(methodOn(EmployeeController.class).employees()).withSelfRel());

        return ResponseEntity.ok().body(entityModels);
    }

    @CrossOrigin
    @GetMapping("/employees/{id}")
    ResponseEntity<?> employee(@PathVariable Long id) {

        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

        EntityModel<Employee> entityModel = assembler.toModel(employee);

        return ResponseEntity.ok().body(entityModel);
    }

    @CrossOrigin
    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) throws URISyntaxException {

        EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));

        return ResponseEntity.created(new URI("")).body(entityModel);
    }

    @CrossOrigin
    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Employee argEmployee, @PathVariable Long id) throws URISyntaxException {

        Employee updatedEmployee = repository.findById(id).map(employee -> {
            employee.setFirstName(argEmployee.getFirstName());
            employee.setLastName(argEmployee.getLastName());
            employee.setDepartmentId(argEmployee.getDepartmentId());
            employee.setHireDate(argEmployee.getHireDate());
            employee.setSalary(argEmployee.getSalary());
            employee.setManagerId(argEmployee.getManagerId());
            employee.setEmail(argEmployee.getEmail());
            employee.setPhoneNumber(argEmployee.getPhoneNumber());
            return repository.save(employee);
        }).orElseGet(() -> {
            argEmployee.setId(id);
            return repository.save(argEmployee);
        });

        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity.created(new URI("")).body(entityModel);
    }

    @CrossOrigin
    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
