package com.management.webapp.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="manager_id")
    private Integer manid;
    
    @Column(name="first_name")
    private String fname;

    	
	@Column(name="Last_Name")
	private String lname;
	
	@Column(name="Employee_id")
	private Integer empid;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Phone_Number")
	private String phonenumber;
	
	@Column(name="Hire_Date")
	private LocalDate hireDate;
	
	@Column(name="Job_Id")
	private String jobid;
	
	@Column(name="SALARY")
	private BigDecimal salary;
	
	@Column(name="Commission_Pct")
	private BigDecimal commissionPct;

    @ManyToOne( cascade = CascadeType.MERGE)
    @JoinColumn(name = "department_id")
    private Department department;



    public Employee(String list, String lname, Integer empid, String email, String phonenumber, LocalDate hireDate,
            String jobid, BigDecimal salary, BigDecimal commissionPct, Department department) {
        this.fname = list;
        this.lname = lname;
        this.empid = empid;
        this.email = email;
        this.phonenumber = phonenumber;
        this.hireDate = hireDate;
        this.jobid = jobid;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.department = department;
    }

    public Integer getManid() {
        return manid;
    }

    public void setManid(Integer manid) {
        this.manid = manid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }



    public Employee(){

    }

    public Employee(int int1, String string, String string2) {
    }




}
