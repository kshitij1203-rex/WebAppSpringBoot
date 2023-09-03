package com.management.webapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.webapp.DTO.MyDTO;
import com.management.webapp.entity.Department;
import com.management.webapp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

   

   

    @Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.department = :department")
    boolean existsByDepartment(@Param("department") Department department);

    Employee findByFname(String managerName);

    List<Employee> findByDepartment(MyDTO department);

    List<Employee> findByDepartment(Department department);

    @Query("SELECt MAX(e.manid) FROM Employee e")
    Integer findMaxEmployeeId();



}
  
    

