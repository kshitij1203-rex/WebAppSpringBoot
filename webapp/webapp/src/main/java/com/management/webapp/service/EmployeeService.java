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

    private final MyService myService;


    
    public EmployeeService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository,
            LocationRepository locationRepository, MyService myService) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.locationRepository = locationRepository;
        this.myService = myService;
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




    // public void addEmployee(EmployeeDTO employeeDTO) {
    //     // Create an Employee entity from the DTO
    //     Employee employee = new Employee();
    //     employee.setFname(employeeDTO.getManagerName());

    //     // Find the department by name
    //     Department department = departmentRepository.findByDeptname(employeeDTO.getDepartmentName());

    //     // Set the department for the employee
    //     employee.setDepartment(department);

    //     // Save the employee to the database
    //     employeeRepository.save(employee);
    // }




        public boolean saveEmployee(EmployeeDTO employeeDTO){
            Department department = departmentRepository.findByDeptname(employeeDTO.getDepartmentName());
            if(department != null && department.getDeptname() != null){
            Employee employee = new Employee(employeeDTO.getManagerName(), null, null, null, null, null, null, null, null, null);
            employeeRepository.save(employee);
                 List<Employee> dEmployees = new ArrayList<>();
                dEmployees.add(employee);
                department.setEmployees(dEmployees);

                String employeeid = department.getEmployeeIds()+","+employee.getManid();
                
                

                department.setEmployeeIds(employeeid);
                departmentRepository.save(department);
                 employee.setDepartment(department);
                employeeRepository.save(employee);
                return true;
            }
            else{
                return false;

            }

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
