package com.pacewisdom.assesment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pacewisdom.assesment.entity.Employee;
import com.pacewisdom.assesment.repository.EmployeeRepository;

@Component
public class EmployeeService {


	@Autowired
	EmployeeRepository repo;

	public List<Employee> getEmployees() {
		return repo.findAll();
	}
	public Optional<Employee> getEmployeeById(long empid) {
		return repo.findById(empid);
	}
	public Employee addNewEmployee(Employee emp) {
		return repo.save(emp);
	}
	public Employee updateEmployee(Employee emp) {
		return repo.save(emp);
	}
	public void deleteEmployeeById(long empid) {
		repo.deleteById(empid);
	}
	public void deleteAllEmployees() {
		repo.deleteAll();
	}
}

