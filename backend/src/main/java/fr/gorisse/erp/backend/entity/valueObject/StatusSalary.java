package fr.gorisse.erp.backend.entity.valueObject;

import lombok.Getter;

@Getter
public enum StatusSalary {
    Employee(1400.00),
    Manager(2105.80),
    Director(2633.30);
    private final double salary;

    StatusSalary(double salary) {
        this.salary = salary;
    }
}