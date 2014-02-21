package com.prakashksinha.mongo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.prakashksinha.mongo.model.Employees;

@Repository
public class EmployeesService {

  @Autowired
  private MongoTemplate mongoTemplate;

  public static final String COLLECTION_NAME = "employees";

  public void addEmployees(Employees employees) {
    if (!mongoTemplate.collectionExists(Employees.class)) {
      mongoTemplate.createCollection(Employees.class);
    }           
    employees.setId(UUID.randomUUID().toString());
    mongoTemplate.insert(employees, COLLECTION_NAME);
  }     

  public List<Employees> listEmployees() {
    return mongoTemplate.findAll(Employees.class, COLLECTION_NAME);
  }     

  public void deleteEmployees(Employees employee) {
    mongoTemplate.remove(employee, COLLECTION_NAME);
  }     

  public void updateEmployees(Employees employee) {
    mongoTemplate.insert(employee, COLLECTION_NAME);    
  }     
}
