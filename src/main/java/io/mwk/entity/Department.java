package io.mwk.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "departments")
public class Department {

    private @Id
    @GeneratedValue
    @Column(name = "department_id")
    Long id;
    private @Column(name = "department_name")
    String name;
    private @Column(name = "manager_id")
    Long managerId;
    public Department() {
    }
    public Department(String name, Long managerId) {
        this.name = name;
        this.managerId = managerId;
    }
}
