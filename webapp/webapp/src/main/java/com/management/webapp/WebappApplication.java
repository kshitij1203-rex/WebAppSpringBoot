package com.management.webapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.management.webapp.Repository.DepartmentRepository;
import com.management.webapp.Repository.EmployeeRepository;
import com.management.webapp.Repository.LocationRepository;
import com.management.webapp.entity.Department;
import com.management.webapp.entity.Employee;
import com.management.webapp.entity.Location;

@SpringBootApplication
public class WebappApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private LocationRepository locationRepository;


	@Autowired
	public WebappApplication(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository,
			LocationRepository locationRepository) {
		this.departmentRepository = departmentRepository;
		this.employeeRepository = employeeRepository;
		this.locationRepository = locationRepository;
	}



	@Override
	public void run(String... args) throws Exception {

		// Location location1 = new Location("Banglore", "123 Bangalore Blvd", "560001", "Karnataka", "INDIA", null);
		// locationRepository.save(location1);

		// Location location2 = new Location("New York", "123 Main St", "10001", "NY", "USA", null);
		// locationRepository.save(location2);

		// Location location3 = new Location("Tokyo", "789 Sakura St", "100-0000", "Tokyo", "JAPAN", null);
		// locationRepository.save(location3);

	

		// // Create the department and populate employeeIds before saving
		
		// //1. department 1
		// Department department1 = new Department("Sales", null, location1);
		// departmentRepository.save(department1);

		// //1. department 2
		// Department department2 = new Department("Human Resource", null, location1);
		// departmentRepository.save(department2);

		// List<Department> departments1 = new ArrayList<>();
		// departments1.add(department1);
		// departments1.add(department2);
		// location1.setDepartments(departments1);
		// locationRepository.save(location1);

		// //1. department 3
		// Department department3 = new Department("Information Technology", null, location2);
		// departmentRepository.save(department3);

		// List<Department> departments2 = new ArrayList<>();
		// departments2.add(department3);
		// location2.setDepartments(departments2);
		// locationRepository.save(location2);

		// //1. department 4
		// Department department4 = new Department("Analyst", null, location3);
		// departmentRepository.save(department4);

		// List<Department> departments3 = new ArrayList<>();
		// departments3.add(department4);
		// location3.setDepartments(departments3);
		// locationRepository.save(location3);




		// // Create and save sample employees
		// Employee employee1 = new Employee("Sophia", "Williams", 1, "sophia.williams@gmail.com", "444-333-2222", LocalDate.of(2023, 6, 10), "Analyst", BigDecimal.valueOf(65000), BigDecimal.valueOf(0.05), department1);
		// employeeRepository.save(employee1);

		// Employee employee2 = new Employee("Michael", "Johnson", 2, "michael.johnson@gmail.com", "555-555-5555", LocalDate.of(2019, 4, 10), "Engineer", BigDecimal.valueOf(70000), BigDecimal.valueOf(0.08), department1);
		// employeeRepository.save(employee2);

		// List<Employee> employees1 = new ArrayList<>();
		// employees1.add(employee1);
		// employees1.add(employee2);
		// department1.setEmployees(employees1);
		// department1.populateEmployeeIds();
		// departmentRepository.save(department1);


		// Employee employee3 = new Employee("Emily", "Brown", 3, "emily.brown@gmail.com", "111-222-3333", LocalDate.of(2022, 3, 5), "Designer", BigDecimal.valueOf(75000), BigDecimal.valueOf(0.07), department2);
		// employeeRepository.save(employee3);

		// Employee employee4 = new Employee("John", "Doe", 4, "john.doe@gmail.com", "123-456-7890", LocalDate.of(2020, 1, 15), "Manager", BigDecimal.valueOf(80000), BigDecimal.valueOf(0.1), department2);
		// employeeRepository.save(employee4);

		// List<Employee> employees2 = new ArrayList<>();
		// employees2.add(employee3);
		// employees2.add(employee4);
		// department2.setEmployees(employees2);
		// department2.populateEmployeeIds();
		// departmentRepository.save(department2);


		// Employee employee5 = new Employee("Peter", "Greg", 5, "peter.greg@gmail.com", "823-000-7890", LocalDate.of(2019, 3, 24), "Technology", BigDecimal.valueOf(90000), BigDecimal.valueOf(0.4), department3);
		// employeeRepository.save(employee5);

		// List<Employee> employees3 = new ArrayList<>();
		// employees3.add(employee5);

		// department3.setEmployees(employees3);
		// department3.populateEmployeeIds();
		// departmentRepository.save(department3);



		// Employee employee6 = new Employee("Jasmine", "Grande", 6, "jasmine.grande@gmail.com", "253-978-7890", LocalDate.of(2013, 4, 12), "Manager", BigDecimal.valueOf(30000), BigDecimal.valueOf(0.9), department4);
		// employeeRepository.save(employee6);

		// List<Employee> employees4 = new ArrayList<>();
		// employees4.add(employee6);

		// department4.setEmployees(employees4);
		// department4.populateEmployeeIds();
		// departmentRepository.save(department4);

    }
}

