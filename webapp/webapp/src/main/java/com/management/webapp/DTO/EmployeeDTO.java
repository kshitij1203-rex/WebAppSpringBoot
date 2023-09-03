package com.management.webapp.DTO;

public class EmployeeDTO {

    private Integer departmentId;
    private String departmentName;
    private String managerName;
    private String city;
    private Integer manid;

 public EmployeeDTO(){
    
 }
    


    public EmployeeDTO(Integer departmentId, String departmentName, String managerName, String city, Integer manid) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.managerName = managerName;
        this.city = city;
        this.manid = manid;
    }
    public Integer getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getManagerName() {
        return managerName;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Integer getManid() {
        return manid;
    }
    public void setManid(Integer manid) {
        this.manid = manid;
    }
    


    
}
