package com.management.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.webapp.DTO.EmployeeDTO;
import com.management.webapp.DTO.MyDTO;
import com.management.webapp.Repository.DepartmentRepository;
import com.management.webapp.Repository.EmployeeRepository;
import com.management.webapp.Repository.LocationRepository;
import com.management.webapp.entity.Department;
import com.management.webapp.entity.Employee;
import com.management.webapp.entity.Location;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {


    
    @Autowired
    public DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public LocationRepository locationRepository;

    public EmployeeService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository,
            LocationRepository locationRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.locationRepository = locationRepository;
    }

    //=============================================================================================================


    //1.
        public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeDTO dto = convertToDTO(employee);
            employeeDTOs.add(dto);
        }

        return employeeDTOs;
    }


    //2.
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setManid(employee.getManid());
        dto.setManagerName(employee.getFname());
        
        if (employee.getDepartment() != null) {
            dto.setDepartmentName(employee.getDepartment().getDeptname());
            
            if (employee.getDepartment().getLoc() != null) {
                dto.setCity(employee.getDepartment().getLoc().getCity());
            }
        }
    
        return dto;
    }

    //3.

    public Integer getMaxEmployeeId(){
        return employeeRepository.findMaxEmployeeId();
    }

//===================================================================================================================
    //4.


    public void addEmployeeToDepartment(String firstName, Integer departmentId) {
        // Retrieve the department from the database
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with ID: " + departmentId));
    
        // Create a new employee entity
        Employee newEmployee = new Employee();
        newEmployee.setFname(firstName);
    
        // Set the department for the new employee
        newEmployee.setDepartment(department);
    
        // Save the new employee
        employeeRepository.save(newEmployee);
    }

//===================================================================================================================

    //5. get employee by id

    public EmployeeDTO getEmployeeById(Integer employeeId){

        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if(employee != null){
            return convertToDTO(employee);
        }
        return null;
    }


    


    
    
}
