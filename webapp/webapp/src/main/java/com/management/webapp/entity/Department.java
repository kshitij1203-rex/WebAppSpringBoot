package com.management.webapp.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="department")
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Department_ID")
    private Integer deptid;

    @Column(name="Department_Name")
    private String deptname;

 
    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();
    
    @Column(name="manager_id", length = 1000) 
    private String employeeIds;

    @ManyToOne
    @JoinColumn(name="Location_Id")
    private Location loc;
    
    @PostLoad
    public void populateEmployeeIds() {
        employeeIds = employees.stream()
            .map(employee -> String.valueOf(employee.getManid()))
            .collect(Collectors.joining(","));
    }

    

    public Department(){

    }

    public Department(String deptname, List<Employee> employees,  Location loc) {
        this.deptname = deptname;
        this.employees = employees;
        this.loc = loc;
    }



    public Department(int int1, String string) {
    }




    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(String employeeIds) {
        this.employeeIds = employeeIds;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

 




   
}
