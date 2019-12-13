package io.mwk.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    private @Id
    @GeneratedValue
    @Column(name = "employee_id")
    Long id;

    private @Column(name = "first_name")
    @NotNull(message = "First name can not be null")
    @Size(min = 2, message = "First name can not less than 2 characters")
    String firstName;

    private @Column(name = "last_name")
    @NotNull(message = "Last name can not be null")
    @Size(min = 2, message = "Last name can not less than 2 characters")
    String lastName;

    private @Column(name = "email")
    String email;

    private @Column(name = "phone_number")
    @Pattern(regexp = "[\\d-]+", message = "Phone number can only contain digits and dashes")
    String phoneNumber;

    private @Column(name = "hire_date")
    LocalDate hireDate;

    private @Column(name = "salary")
    @NotNull(message = "salary can not be null")
    @Positive(message = "salary can not be 0 or less")
    Double salary;

    private @Column(name = "manager_id")
    Long managerId;

    private @Column(name = "department_id")
    Long departmentId;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName, String email, String phoneNumber, LocalDate hireDate, Double salary, Long managerId, Long departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.salary = salary;
        this.managerId = managerId;
        this.departmentId = departmentId;
    }
}
