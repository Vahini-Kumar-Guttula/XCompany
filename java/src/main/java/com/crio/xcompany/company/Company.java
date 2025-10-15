package com.vk.xcompany.company;

import java.util.*;

public class Company {
    private String companyName;
    private Employee founder;
    private Map<String, Employee> employeeBook;

    private Company(String companyName, Employee founder) {
        this.companyName = companyName;
        this.founder = founder;
        employeeBook = new HashMap<>();
        employeeBook.put(founder.getName(), founder);
    }

    public static Company create(String companyName, Employee founder) {
        return new Company(companyName, founder);
    }

    public String getCompanyName() {
        return companyName;
    }


    public void registerEmployee(String employeeName, Gender gender) {
        if (!employeeBook.containsKey(employeeName)) {
            Employee emp = new Employee(employeeName, gender);
            employeeBook.put(employeeName, emp);
        }
    }


    public Employee getEmployee(String employeeName) {
        return employeeBook.get(employeeName);
    }


    public void deleteEmployee(String employeeName) {
        Employee emp = employeeBook.get(employeeName);
        if (emp != null) {
            Employee mgr = emp.getManager();
            if (mgr != null) {
                mgr.getDirectReports().remove(emp);
            }
            employeeBook.remove(employeeName);
        }
    }


    public void assignManager(String employeeName, String managerName) {
        Employee emp = employeeBook.get(employeeName);
        Employee mgr = employeeBook.get(managerName);
        if (emp != null && mgr != null) {
            emp.assignManager(mgr);
        }
    }


    public List<Employee> getDirectReports(String managerName) {
        Employee mgr = employeeBook.get(managerName);
        if (mgr == null) return Collections.emptyList();
        return mgr.getDirectReports();
    }


    public List<Employee> getTeamMates(String employeeName) {
        Employee emp = employeeBook.get(employeeName);
        if (emp == null) return Collections.emptyList();
        return emp.getTeamMates();
    }


    public List<List<Employee>> getEmployeeHierarchy(String managerName) {
        List<List<Employee>> hierarchy = new ArrayList<>();
        Employee manager = employeeBook.get(managerName);
        if (manager == null) return hierarchy;

        Queue<Employee> queue = new LinkedList<>();
        queue.add(manager);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Employee> levelList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Employee emp = queue.poll();
                levelList.add(emp);

                for (Employee report : emp.getDirectReports()) {
                    queue.add(report);
                }
            }
            hierarchy.add(levelList);
        }
        return hierarchy;
    }
}
