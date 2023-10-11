package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// fields
	private EmployeeService employeeService;

	// constructor
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// list of employees
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		// get the employees
		List<Employee> employees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", employees);

		// render the view
		return "employees/list-employees";
	}

	// show employee form
	@GetMapping("/showFormForAdd")
	public String addEmployee(Model model){
		model.addAttribute("employee", new Employee());
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee savedEmployee){
		// save the employee
		employeeService.save(savedEmployee);

		// redirect to the list page
		return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate")
	public String updateForm(@RequestParam("employeeId") int employeeId , Model model){
		// get the employee from the db
		Employee employeeToUpdate = employeeService.findById(employeeId);

		// return back to the list if not able to find the employee
		if(employeeToUpdate == null){
			return "redirect:/employees/list";
		}

		// set the employee on the model
		model.addAttribute("employee", employeeToUpdate);

		// was able to find the employee
		return "employees/employee-form";
	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int employeeId){
		// delete the employee
		employeeService.deleteById(employeeId);

		// redirect to the employees list
		return "redirect:/employees/list";
	}

	// redirect anything with /employees/** to the /employees/list
	@GetMapping("/*")
	public String home(HttpServletResponse response){
		return "redirect:/employees/list";
	}

}









