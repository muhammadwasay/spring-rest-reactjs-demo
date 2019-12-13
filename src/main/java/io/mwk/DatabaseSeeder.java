package io.mwk;

import io.mwk.entity.Department;
import io.mwk.entity.Employee;
import io.mwk.repository.DepartmentRepository;
import io.mwk.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DatabaseSeeder {
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        return args -> {
            departmentRepository.save(new Department("IT", 1l));
            departmentRepository.save(new Department("Admin", 2l));
            departmentRepository.save(new Department("HR", 3l));
            departmentRepository.save(new Department("Operations", 3l));
            departmentRepository.save(new Department("Security", 3l));
            employeeRepository.save(new Employee("Hanif", "Khan", "email.example.com", "00971556556765", LocalDate.now(), 200000.00, 1l, 2l));
            employeeRepository.save(new Employee("Shaid", "Khan", "email.example.com", "00971556556765", LocalDate.now(), 200000.00, 1l, 2l));
            employeeRepository.save(new Employee("Shah", "Khan", "email.example.com", "00971556556765", LocalDate.now(), 200000.00, 1l, 2l));
            employeeRepository.save(new Employee("Ghafoor", "Khan", "email.example.com", "00971556556765", LocalDate.now(), 200000.00, 1l, 2l));
            employeeRepository.save(new Employee("Shakoor", "Khan", "email.example.com", "00971556556765", LocalDate.now(), 200000.00, 1l, 2l));
            employeeRepository.save(new Employee("Muhammad", "Khan", "email.example.com", "00971556556765", LocalDate.now(), 200000.00, 1l, 2l));
        };
    }
}
