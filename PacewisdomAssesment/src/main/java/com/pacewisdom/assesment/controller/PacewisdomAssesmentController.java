package com.pacewisdom.assesment.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pacewisdom.assesment.entity.Employee;
import com.pacewisdom.assesment.service.EmployeeService;

@Controller
public class PacewisdomAssesmentController {
	
	private static final Logger log = LoggerFactory.getLogger(PacewisdomAssesmentController.class);

	@Autowired
	EmployeeService service;

	@RequestMapping(value= "/employee/all", method= RequestMethod.GET)
	public List<Employee> getEmployees() {
		log.info(" - Get all employees service is invoked.");
		return service.getEmployees();
	}

	@RequestMapping(value= "/employee/{id}", method= RequestMethod.GET)
	public Employee getEmployeeById(@PathVariable int id) throws Exception {
		log.info(" Get employee details by id is invoked.");

		Optional<Employee> emp =  service.getEmployeeById(id);
		if(!emp.isPresent())
			throw new Exception("Could not find employee with id- " + id);

		return emp.get();
	}

	@RequestMapping(value= "/employee/add", method= RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee newemp) {
		log.info(" Create new employee method is invoked.");
		return service.addNewEmployee(newemp);
	}

	@RequestMapping(value= "/employee/update/{id}", method= RequestMethod.PUT)
	public Employee updateEmployee(@RequestBody Employee updemp, @PathVariable int id) throws Exception {
		log.info(" Update employee details by id is invoked.");

		Optional<Employee> emp =  service.getEmployeeById(id);
		if (!emp.isPresent())
			throw new Exception("Could not find employee with id- " + id);

		if(updemp.getFirstName() == null || updemp.getFirstName().isEmpty())
			updemp.setFirstName(emp.get().getFirstName());
		if(updemp.getLastName() == null || updemp.getLastName().isEmpty())
			updemp.setLastName(emp.get().getLastName());
		if(updemp.getAddress() == null || updemp.getAddress().isEmpty())
			updemp.setAddress(emp.get().getAddress());
		if(updemp.getEmailId() == null || updemp.getEmailId().isEmpty())
			updemp.setEmailId(emp.get().getEmailId());
		if(updemp.getGender() == null || updemp.getGender().isEmpty())
			updemp.setGender(emp.get().getGender());

		// Required for the "where" clause in the sql query template.
		updemp.setId(id);
		return service.updateEmployee(updemp);
	}

	@RequestMapping(value= "/employee/delete/{id}", method= RequestMethod.DELETE)
	public void deleteEmployeeById(@PathVariable int id) throws Exception {
		log.info(" Delete employee by id is invoked.");

		Optional<Employee> emp =  service.getEmployeeById(id);
		if(!emp.isPresent())
			throw new Exception("Could not find employee with id- " + id);

		service.deleteEmployeeById(id);
	}

	@RequestMapping(value= "/employee/deleteall", method= RequestMethod.DELETE)
	public void deleteAll() {
		log.info("Delete all employees is invoked.");
		service.deleteAllEmployees();
	}

}
