package com.management.webapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.management.webapp.DTO.EmployeeDTO;
import com.management.webapp.DTO.MyDTO;
import com.management.webapp.Repository.DepartmentRepository;
import com.management.webapp.Repository.EmployeeRepository;
import com.management.webapp.entity.Department;
import com.management.webapp.entity.Employee;
import com.management.webapp.service.EmployeeService;
import com.management.webapp.service.MyService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Controller
public class MyController {

    @Autowired
    private MyService myService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentRepository departmentRepository;

    private EmployeeRepository employeeRepository;

       private final EntityManager entityManager; // Inject the EntityManager


 
    public MyController(MyService myService, EmployeeService employeeService, DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository, EntityManager entityManager) {
        this.myService = myService;
        this.employeeService = employeeService;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.entityManager = entityManager;
    } 



//=====================================================================================================
//8090
    // First Page where all departments are shown




    @GetMapping("/vscode")
    public String showAllDepartments(Model model){
        model.addAttribute("departments", myService.getAllDepartments());
        return "departments";
    }

    
    // @GetMapping("/employees")
    // public String getEmployeeList(Model model) {
    //     List<EmployeeDTO> employees = employeeService.getAllEmployees();
    //     model.addAttribute("employees", employees);
    //     return "employee-list";
    // }
//====================================================================================================

    //second page where ADD new Entry for department should be done

  

    @GetMapping("/vscode/add-entry")
    public String showAddEntryForm(Model model) {

        // Calculate the new department ID by finding the maximum department ID and incrementing it
    Integer maxDepartmentId = myService.getMaxDepartmentId(); 
    Integer newDepartmentId = maxDepartmentId != null ? maxDepartmentId + 1 : 1;

        // Prepare an empty ProjectDTO object for the form
        MyDTO projectDTO = new MyDTO();
        projectDTO.setDepartmentId(newDepartmentId);
        model.addAttribute("projectDTO", projectDTO);
        
        return "add-entry"; // Return the name of the HTML template
    }


//=====================================================================================================
    // This is for Saving Department inside second page

        @PostMapping("/vscode")
        public String saveDepartment( @ModelAttribute("projectDTO") MyDTO projectDTO) {

            if(myService.saveDepartment(projectDTO)){
        
        return "redirect:/vscode";
            }
            return "redirect:/vscode";

        
         // Redirect to the department list page
    }

//=====================================================================================================

@RequestMapping("/vscode/department")
@ResponseBody
public MyDTO showDepartmentDetails(@RequestParam("Id") Integer Id, Model model) {
    MyDTO projectDTO = myService.getDepartmentById(Id);

        return myService.getDepartmentById(Id);  
}


//=====================================================================================================

// @RequestMapping(value="/vscode/update", method = {RequestMethod.PUT, RequestMethod.GET})
// public ResponseEntity<MyDTO> updateDepartmentDetails(@RequestBody MyDTO projectDTO) {
   
//     Department myDTO =   myService.updateDepartmentDetailsFromDTO(projectDTO);
//     return ResponseEntity.ok(HttpStatus.OK).body(myDTO);

// }

//     model.addAttribute("projectDTO", updatedDepartment);

//     if (updatedDepartment != null) {
//         // Specify the view name
//         return new RedirectView("/vscode");
//     } else {
//         return new RedirectView("/vscode?showModal=true");
//     }


// @RequestMapping(value="/vscode/update", method = {RequestMethod.PUT, RequestMethod.GET})
// public String updateDepartmentDetails(@PathVariable(value="Id")@ModelAttribute MyDTO projectDTO, Model model) {
   
//      myService.updateDepartmentDetailsFromDTO(projectDTO);
    
//      Department updatedDepartment= myService.updateDepartmentDetailsFromDTO(projectDTO);
//     model.addAttribute("projectDTO", updatedDepartment);
//      return "redirect:/vscode";
// }


//  @RequestMapping(value="/vscode/update{id}", method = {RequestMethod.PUT, RequestMethod.GET})
//     public ResponseEntity<String> updateDepartment(@PathVariable Integer id, @RequestBody MyDTO departmentDTO)throws Exception {
//         System.out.println(departmentDTO.getManagerName());
//         myService.updateDepartment(id, departmentDTO.getManagerName());
//         String successmsg="department updated";
//         ResponseEntity<String> response = new ResponseEntity<String>(successmsg, HttpStatus.OK);
//         return response;
        
         
//     }
    //Update/Edit popup in Department Page
//  @RequestMapping(value="/vscode/update", method = {RequestMethod.PUT, RequestMethod.GET})
//     public String updateDepartment(@ModelAttribute("department") MyDTO departmentDTO) {
//         System.out.println(departmentDTO.getManagerName());
//         int Response = myService.updateDepartment(departmentDTO);
//             if(Response == 200){
//             return "redirect:/vscode1234";
//             }
//             else{
//             return "redirect:/vscode99999999";
//             }// Redirect to the department list page
//     }

//   @RequestMapping(value="/vscode/update", method = {RequestMethod.PUT, RequestMethod.GET})
//         public String updateDepartmentDetails1( @ModelAttribute("projectDTO") MyDTO projectDTO) {

//         myService.updateDepartmentDetailsFromDTO1(projectDTO);
//         return "redirect:/vscode";
        
//          // Redirect to the department list page
//     }



//===========================================================================
    // // Update/Edit popup in Department Page
    // @RequestMapping(value="/vscode/update", method = {RequestMethod.PUT, RequestMethod.GET})
    // public String updateDepartmentDetails(@ModelAttribute("department") MyDTO projectDTO) {
      
    //        Department updatedDepartment = myService.updateDepartmentDetailsFromDTO( projectDTO);
        
        
    //         return "redirect:/vscode";
    //      // Redirect to the department list page
    // }
  







// @PutMapping("/vscode/update")
// @ResponseBody // Return JSON response
// public ResponseEntity<String> updateDepartmentDetails(
//     @PathVariable Integer departmentId,
//     @ModelAttribute MyDTO departmentDTO
// ) {
//     Department updatedDepartment = myService.updateDepartmentDetailsFromDTO(departmentId, departmentDTO);

//     if (updatedDepartment != null) {
//         return ResponseEntity.ok("Department updated successfully");
//     } else {
//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not found");
//     }
// }






    
    // @PutMapping("/{departmentId}")
    // public ModelAndView updateDepartmentDetails(
    //     @PathVariable Integer departmentId, // Change the path variable type to Integer
    //     @ModelAttribute MyDTO dto,
    //     Model model
    // ) {
    //     Department updatedDepartment = myService.updateDepartmentDetailsFromDTO(departmentId, dto);

    //     if (updatedDepartment != null) {
    //         model.addAttribute("successMessage", "Department updated successfully");
    //         return new ModelAndView("updateSuccess");
    //     } else {
    //         return new ModelAndView("error404"); // Handle not found error page
    //     }
    // }









    // @PostMapping("/vscode/update")
    // public String updateDepartment(
    //         @RequestParam("departmentId") Integer departmentId,
    //         @RequestParam("newDepartmentName") String newDepartmentName,
    //         @RequestParam("newFirstName") String newFirstName,
    //         @RequestParam("newCity") String newCity,
    //         Model model) {
    //     String resultMessage = myService.updateDepartment(departmentId, newDepartmentName, newFirstName, newCity);
    //     model.addAttribute("message", resultMessage); // Set the validation message

    //     if (resultMessage.equals("Update successful.")) {
    //         return "redirect:/vscode"; // Replace with your success page
    //     } else {
    //         return "update-department"; // Return to the form page with the validation message
    //     }
    // }
// Save Edit popup

    //     @PostMapping("/vscode")
    //     public String saveEdit( @ModelAttribute("projectDTO") MyDTO projectDTO) {

    //     myService.saveDepartment(projectDTO);
    //     return "redirect:/vscode";
        
    //      // Redirect to the department list page
    // }


//=====================================================================================================

//=====================================================================================================

    //Delete Method for Department Page

    @RequestMapping(value="/vscode/delete", method={RequestMethod.DELETE, RequestMethod.GET})
    @ResponseBody
    public RedirectView deleteDepartment(@RequestParam("id") Integer departmentId, Model model) {
    int Response = myService.deleteDepartment(departmentId);
        if(Response == 200){
        return new RedirectView("/vscode");
        }   
        else{
         return new RedirectView("/vscode?showModal=true");
        }

    }

//==================================================================================================================

   //Employee page
        
//2. when we want to show all the employees 
    @GetMapping("/vscode/employees")
    public String getEmployeeList(@RequestParam("Id") Integer departmentId,Model model) {

         MyDTO departmentDTO = myService.getDepartmentById(departmentId);
         model.addAttribute("departments", departmentDTO);



        String sqlQuery = "SELECT department_name FROM vscode2.department";
        Query query = entityManager.createNativeQuery(sqlQuery);
        List<String> departmentNames = query.getResultList();
        model.addAttribute("departmentNames", departmentNames);

        // List<EmployeeDTO> employees = employeeService.getAllEmployees();
        // model.addAttribute("employees", employees);

    

        return "employee";
    }


    //========================================================================================================

    //employee list page for particular department

          
    @GetMapping("/vscode/employee/D")
    public String showEmployeePage(@RequestParam("departments") String departmentName, Model model) {
     
        if (departmentName != null && !departmentName.isEmpty()) {
        List<Object[]> salesResults = departmentRepository.findDepartmentDataByName(departmentName);
        model.addAttribute("sales", salesResults);

    }
        String sqlQuery = "SELECT department_name FROM vscode2.department";
        Query query = entityManager.createNativeQuery(sqlQuery);
        List<String> departmentNames = query.getResultList();
        model.addAttribute("departmentNames", departmentNames);


        return "dropdown"; // This corresponds to the name of your HTML template for the employee page
    }

    // String sqlQuery2 = 
    // "SELECT w1.department_name, w2.first_name, w3.city, w1.department_id 
    // FROM vscode2.department w1  
    // JOIN vscode2.employee w2 ON (w1.department_id = w2.department_id) 
    //  JOIN vscode2.location w3 USING (location_id) 
    //  WHERE w1.department_name = 'Human Resource';";
    //     Query query2 = entityManager.createNativeQuery(sqlQuery2);
    //     List<String> departmentNames1 = query2.getResultList();
    //     model.addAttribute("sales", departmentNames1);        
        
    //     return "employee"; // Thymeleaf template name
    // }


     //==================================================================================================
     //add Employee Entry page
    
    @GetMapping("/vscode/employee/add-employee")
    public String showEmployeeEntryForm(Model model, String departmentName) {

        // Calculate the new department ID by finding the maximum department ID and incrementing it
    Integer maxDepartmentId = employeeService.getMaxEmployeeId(); 
    Integer newDepartmentId = maxDepartmentId != null ? maxDepartmentId + 1 : 1;

        // Prepare an empty ProjectDTO object for the form
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setManid(newDepartmentId);
        model.addAttribute("employeeDTO", employeeDTO);
        
      

        // String sqlQuery = "SELECT department_name FROM vscode2.department";
        // Query query = entityManager.createNativeQuery(sqlQuery);
        // List<String> departmentNames = query.getResultList();
        // model.addAttribute("departmentNames", departmentNames);

        
        
        return "add-employee"; // Return the name of the HTML template
    }


 //====================================================================================================================   
 
// save method for adding employee into department
        @PostMapping("/vscode/allEmployees")
        public String saveEmployee( @ModelAttribute("projectDTO") EmployeeDTO employeeDTO) {

        // employeeService.addEmployeeToDepartment(employeeDTO.getManid(), employeeDTO.getDepartmentId());
        boolean success =  employeeService.saveEmployee(employeeDTO);
        if(success){
            return "redirect:/vscode/allEmployees";
        }

       
        return "redirect:/vscode/allEmployees";
        
         // Redirect to the department list page
    }


    // @PostMapping("/vscode/allEmployees")
    // public String saveEmployee(@ModelAttribute EmployeeDTO employeeDTO) {
    //     try {
    //         // Call the service method to add the employee to the department
    //         employeeService.addEmployeeToDepartment(employeeDTO.getManagerName(), employeeDTO.getDepartmentId());

    //         return "/vscode/allEmployees"; // Redirect to the employee list page after saving
    //     } catch (Exception e) {
    //         // Handle any exceptions that may occur (e.g., validation errors)
    //         // You can add error messages and display them in the form
    //         return "add-employee"; // Return to the add-employee form with error messages
    //     }
    // }






//==============================================================================================================
  
//Show All Employees Page


   @GetMapping("/vscode/allEmployees")
    public String showEmployeePage(  Model model) {


        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "allemployees"; // This corresponds to the name of your HTML template for the employee page
    }





//======================================================================================================
    //move action popup in Employee Page

    @PostMapping("/vscode/employee/move-manager")
    public String moveManager(@RequestParam("currentDepartmentId") Integer currentDepartmentId,
                              @RequestParam("newDepartmentId") Integer newDepartmentId) {
        
        return "redirect:/vscode/employee"; // Redirect back to the department list page
    }


}

 

//======================================================================================================









    
