package com.management.webapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;

import com.management.webapp.DTO.MyDTO;
import com.management.webapp.entity.Department;
import com.management.webapp.entity.Employee;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {


    @Query("SELECt MAX(d.deptid) FROM Department d")
    Integer findMaxDepartmentId();

    // @Query("SELECT d.deptname FROM Department d")
    // List<Department> findAllDepartmentNames();
    

    // @Query("SELECT new com.management.webapp.DTO.MyDTO(d.deptname, e.fname, l.city, d.deptid) " +
    // "FROM Department d " +
    // "JOIN Employee e ON d.deptid = e.department.deptid " +
    // "JOIN Location l ON d.loc.locid = l.locid " +
    // "WHERE d.deptname = :val")
    // List<MyDTO> fetchUsingDepartments(@Param("val") String name);

    @Query("SELECT d.deptid , e.manid ,d.deptname, e.fname, l.city  FROM Department d " +
           "JOIN d.employees e " +
           "JOIN d.loc l " +
           "WHERE d.deptname = :departmentName")
    List<Object[]> findDepartmentDataByName(@Param("departmentName") String departmentName);
}
