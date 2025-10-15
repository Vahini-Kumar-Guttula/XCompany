package com.vk.xcompany.company;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private Gender gender;
    private Employee manager;
    private List<Employee> directReports;

    public Employee(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        this.directReports = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Employee getManager() {
        return manager;
    }

    public void assignManager(Employee manager) {
        this.manager = manager;
        if (manager != null) {
            manager.directReports.add(this);
        }
    }


    public List<Employee> getDirectReports() {
        return new ArrayList<>(directReports);
    }


    public List<Employee> getTeamMates() {
        List<Employee> teammates = new ArrayList<>();

        if (manager != null) {
            teammates.add(manager);

            for (Employee emp : manager.directReports) {
                teammates.add(emp);
            }
        }
        return teammates;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", gender=" + gender + "]";
    }
}
