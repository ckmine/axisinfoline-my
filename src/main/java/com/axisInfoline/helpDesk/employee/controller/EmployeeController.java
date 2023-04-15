package com.axisInfoline.helpDesk.employee.controller;

import com.axisInfoline.helpDesk.core.domain.Count;
import com.axisInfoline.helpDesk.employee.domain.Employee;
import com.axisInfoline.helpDesk.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"https://portal.axisinfoline.com","http://localhost:3000"})
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping(value = "/addEmployee/loggedInUserId/{loggedInUserId}")
    public String addEmployee(@RequestBody Employee employee, @PathVariable String loggedInUserId) throws Exception {
        return employeeService.addEmployee(employee, loggedInUserId);
    }

    @PatchMapping(value = "/updateEmployee/loggedInUserId/{loggedInUserId}")
    public String updateEmployee(@RequestBody Employee employee, @PathVariable String loggedInUserId) throws Exception {
        return employeeService.updateEmployee(employee,loggedInUserId);
    }

    @PatchMapping(value = "/updatePassword/loggedInUserId/{loggedInUserId}")
    public String updatePassword(@RequestBody Employee employee, @PathVariable String loggedInUserId) throws Exception {
        return employeeService.updatePassword(employee, loggedInUserId);
    }

    @GetMapping(value = "/getAllEngineers/{status}")
    public List<Employee> getAllEngineers(@PathVariable String status){
        return employeeService.getAllEngineers(status);
    }

    @GetMapping(value = "/getAllAeit/{status}")
    public List<Employee> getAllAeit(@PathVariable String status){
        return employeeService.getAllAeit(status);
    }

    @PostMapping(value = "/authenticated")
    public Employee authenticated(@RequestBody Employee employee){
        return employeeService.authenticated(employee);
    }

    @GetMapping(value = "/employee/{id}")
    public Employee getEmployeeById(@PathVariable String id){
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping(value = "/deleteEmployee/{id}")
    public String deleteEmployeeById(@PathVariable String id){
        return employeeService.deleteEmployeeById(id);
    }

}
