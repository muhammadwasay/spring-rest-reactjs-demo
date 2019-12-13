package io.mwk.controller;

import io.mwk.entity.Department;
import io.mwk.exception.DepartmentNotFoundException;
import io.mwk.repository.DepartmentRepository;
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
public class DepartmentController {

    private final DepartmentRepository repository;

    private final DepartmentResourceAssembler assembler;

    DepartmentController(DepartmentRepository repository, DepartmentResourceAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    @CrossOrigin
    @GetMapping("/departments")
    ResponseEntity<?> departments() {

        List<EntityModel<Department>> departments = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

        CollectionModel<EntityModel<Department>> entityModels = new CollectionModel<>(departments, linkTo(methodOn(DepartmentController.class).departments()).withSelfRel());

        return ResponseEntity.ok().body(entityModels);
    }

    @CrossOrigin
    @GetMapping("/departments/{id}")
    ResponseEntity<?> department(@PathVariable Long id) {

        Department department = repository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));

        EntityModel<Department> entityModel = assembler.toModel(department);

        return ResponseEntity.ok().body(entityModel);
    }

    @CrossOrigin
    @PostMapping("/departments")
    ResponseEntity<?> newDepartment(@RequestBody Department newdepartment) throws URISyntaxException {

        EntityModel<Department> entityModel = assembler.toModel(repository.save(newdepartment));

        return ResponseEntity.created(new URI("")).body(entityModel);
    }

    @CrossOrigin
    @PutMapping("/departments/{id}")
    ResponseEntity<?> replaceDepartment(@RequestBody Department argDepartment, @PathVariable Long id) throws URISyntaxException {

        Department updatedDepartment = repository.findById(id).map(department -> {
            department.setName(argDepartment.getName());
            department.setManagerId(argDepartment.getManagerId());
            return repository.save(department);
        }).orElseGet(() -> {
            argDepartment.setId(id);
            return repository.save(argDepartment);
        });

        EntityModel<Department> entityModel = assembler.toModel(updatedDepartment);

        return ResponseEntity.created(new URI("")).body(entityModel);
    }

    @CrossOrigin
    @DeleteMapping("/departments/{id}")
    ResponseEntity<?> deleteDepartment(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
