package com.prakashksinha.mongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.prakashksinha.mongo.model.Employees;
import com.prakashksinha.mongo.service.EmployeesService;

@Controller
public class EmployeesController {

  @Autowired
  private EmployeesService employeesService;

  @RequestMapping(value = "/employees", method = RequestMethod.GET)  
  public String getEmployeesList(ModelMap model) {  
    try {
      model.addAttribute("employeesList", employeesService.listEmployees());
	} catch (Exception e) {
	  System.out.println("Error:" + e.getCause());
	}
	return "display";    
  }  
  
  @RequestMapping(value = "/employees/save", method = RequestMethod.POST)  
  public View createEmployees(@ModelAttribute Employees employees, ModelMap model) {
    if(StringUtils.hasText(employees.getId())) { 
      employeesService.updateEmployees(employees);
    } else if (StringUtils.hasLength(employees.getName()) ) { 
      employeesService.addEmployees(employees);
    } 
    return new RedirectView("/MongoSpring01/employees");  
  } 
  
  @RequestMapping(value = "/employees/delete", method = RequestMethod.GET)  
  public View deleteEmployees(@ModelAttribute Employees employees, ModelMap model) {  
    employeesService.deleteEmployees(employees);  
    return new RedirectView("/MongoSpring01/employees");      
  }  
}
