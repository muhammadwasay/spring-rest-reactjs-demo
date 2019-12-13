package io.mwk.controller;

import io.mwk.entity.Department;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class DepartmentResourceAssembler implements RepresentationModelAssembler<Department, EntityModel<Department>> {

    @Override
    public EntityModel<Department> toModel(Department department) {
        return new EntityModel<>(department, linkTo(methodOn(DepartmentController.class).department(department.getId())).withSelfRel(), linkTo(methodOn(DepartmentController.class).departments()).withRel("departments"));
    }
}
