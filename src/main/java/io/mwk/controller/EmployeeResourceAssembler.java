package io.mwk.controller;

import io.mwk.entity.Employee;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class EmployeeResourceAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    @Override
    public EntityModel<Employee> toModel(Employee employee) {
        return new EntityModel<>(employee, linkTo(methodOn(EmployeeController.class).employee(employee.getId())).withSelfRel(), linkTo(methodOn(EmployeeController.class).employees()).withRel("employees"));
    }
}
