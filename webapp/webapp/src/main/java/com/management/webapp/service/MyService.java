package com.management.webapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.management.webapp.DTO.MyDTO;
import com.management.webapp.Repository.DepartmentRepository;
import com.management.webapp.Repository.EmployeeRepository;
import com.management.webapp.Repository.LocationRepository;
import com.management.webapp.entity.Department;
import com.management.webapp.entity.Employee;
import com.management.webapp.entity.Location;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class MyService {

    @Autowired
    public DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public LocationRepository locationRepository;

    private final JdbcTemplate jdbcTemplate;
    private EntityManagerFactory entityManagerFactory;

    public MyService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository,
            LocationRepository locationRepository, JdbcTemplate jdbcTemplate,
            EntityManagerFactory entityManagerFactory) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.locationRepository = locationRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.entityManagerFactory = entityManagerFactory;
    }

    // ======================================================================================================

    // 1. GetAll - Method

    public List<MyDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // public List<MyDTO> getAllEmployees(){
    // List<Department> departments = departmentRepository.findAllDepartmentNames();
    // return departments.stream()
    // .map(this::convertToDto)
    // .collect(Collectors.toList());
    // }

    // ======================================================================================================

    // 2. Get-BY-ID-Method

    public MyDTO getDepartmentById(Integer departmentId) {

        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department != null) {
            return convertToDto(department);

        }
        return null;
    }

    // ======================================================================================================

    // 3. Get Max-Department-ID Method

    public Integer getMaxDepartmentId() {
        return departmentRepository.findMaxDepartmentId();
    }

    // 4. Get Max-Employee-ID method

    // ======================================================================================================

    // 4. Save method for add new entry will work in ADD-NEW-ENTRY Page

    public void saveDepartment(MyDTO projectDTO) {

        Department department = new Department(projectDTO.getDepartmentName(), null, null);
        departmentRepository.save(department);

        Location location = null;
        if (projectDTO.getCity() != "") {
            location = new Location(projectDTO.getCity(), null, null, null, null, null);
            locationRepository.save(location);
            List<Department> departments = new ArrayList<>();
            departments.add(department);
            location.setDepartments(departments);
            locationRepository.save(location);
            department.setLoc(location);
            departmentRepository.save(department);
        }

        List<Employee> employees = new ArrayList<>();
        Employee manager = null;
        if (projectDTO.getManagerName() != "") {
            manager = new Employee(projectDTO.getManagerName(), null, null, null, null, null, null, null, null,
                    department);
            employees.add(manager);
            employeeRepository.save(manager);
            department.setEmployees(employees);
            department.populateEmployeeIds();
            departmentRepository.save(department);
        }
    }
    // ======================================================================================================

    // 4. Delete method

    public Integer deleteDepartment(Integer departmentId) {

        Department department = departmentRepository.findById(departmentId).orElse(null);

        if (department != null) {
            boolean employeePresent = employeeRepository.existsByDepartment(department);
            if (employeePresent) {
                return 500;
            } else {
                departmentRepository.delete(department);
                return 200;
            }

        }
        return 404;

    }

    // ======================================================================================================

    // 5. Update Method for Department Page
    

    public Department updateDepartmentDetailsFromDTO(MyDTO dto) {
        // Fetch the existing Department entity by ID
        Department department = departmentRepository.findById(dto.getDepartmentId()).orElse(null);

        if (department != null) {
            // Update the department name
            department.setDeptname(dto.getDepartmentName());

            // Fetch the manager (Employee) by manid and update the manager's first name
            Employee manager = employeeRepository.findById(dto.getManid()).orElse(null);
            if (manager != null) {
                manager.setFname(dto.getManagerName());
                employeeRepository.save(manager);
            }
           Location locId = department.getLoc();
            // Fetch the location (Location) by locid and update the location's city
            Optional<Location> location = locationRepository.findById(locId.getLocid());
            if (location.isPresent()) {
                Location loc = location.get();
                loc.setCity(dto.getCity());
                locationRepository.save(loc);
            }

            // Save the updated department
            return departmentRepository.save(department);
        }

        return null; // Department not found
    }






    // ======================================================================================================

    // 6.Method to Convert Entity into DTO

    private MyDTO convertToDto(Department department) {
        MyDTO dto = new MyDTO();
        dto.setDepartmentId(department.getDeptid());
        dto.setDepartmentName(department.getDeptname());
        if (!department.getEmployees().isEmpty()) {
            // Retrieve any employee from the list
            Employee anyEmployee = department.getEmployees().get(0);
            dto.setManagerName(anyEmployee.getFname());

            if (department.getLoc() != null) {
                dto.setCity(department.getLoc().getCity());
            }

        }
        return dto;
    }

    // ======================================================================================================

}
