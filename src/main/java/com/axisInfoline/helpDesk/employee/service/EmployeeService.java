package com.axisInfoline.helpDesk.employee.service;

import com.axisInfoline.helpDesk.admin.service.AdminService;
import com.axisInfoline.helpDesk.core.domain.Count;
import com.axisInfoline.helpDesk.employee.domain.Employee;
import com.axisInfoline.helpDesk.employee.repository.EmployeeJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
public class EmployeeService {

    @Autowired
    EmployeeJpaRepository employeeJpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public String addEmployee(Employee employee, String loggedInUserId) throws Exception {
        if(isAdmin(loggedInUserId) || isSuperAdmin(loggedInUserId)){
            //        if(!isUserWithCircleExists(employee.getPhone())){
            employee.setId(getMd5(employee.getName()+employee.getPhone()+employee.getCircle()));
            employeeJpaRepository.save(employee);
            return "Employee Added";
//        } else {
//            throw new Exception("User Already Exists for circle: "+employee.getCircle());
//        }
        } else {
            throw new Exception("You are not authorized to Add Employee");
        }
    }

    public Boolean isUserWithCircleExists(String circle){
        Double count = ((Number) entityManager.createNativeQuery("select  count(*) from helpdesk.employee where circle=:circle", Double.class).setParameter("circle","circle").getSingleResult()).doubleValue();
        return count > 0 ? true : false;
    }

    @Transactional
    public String updateEmployee(Employee employee, String loggedInUserId) throws Exception {
        if(isAdmin(loggedInUserId) || isSuperAdmin(loggedInUserId)){
            entityManager.createNativeQuery("UPDATE helpdesk.employee set name=:name, phone=:phone, circle=:circle, status=:status WHERE id=:id")
                    .setParameter("name", employee.getName())
                    .setParameter("phone", employee.getPhone())
                    .setParameter("circle", employee.getCircle())
                    .setParameter("status", employee.getStatus())
                    .setParameter("id", employee.getId())
                    .executeUpdate();
            return "Employee Successfully updated";
        } else {
            throw new Exception("You are not authorized to Update Employee");
        }
    }

    @Transactional
    public String updatePassword(Employee employee, String loggedInUserId) throws Exception {
        if(isAdmin(loggedInUserId) || isSuperAdmin(loggedInUserId)){
            entityManager.createNativeQuery("UPDATE helpdesk.employee set password=:password WHERE id=:id")
                    .setParameter("password", employee.getPassword())
                    .setParameter("id", employee.getId())
                    .executeUpdate();
            return "Password Successfully updated";
        } else {
            throw new Exception("You are not authorized to Reset Passworf");
        }
    }

    public Employee authenticated(Employee employee){
        return employeeJpaRepository.findByPhoneAndPassword(employee.getPhone(), employee.getPassword());
    }

    public Employee getEmployeeById(String id){
        return employeeJpaRepository.findById(id);
    }

    @Transactional
    public String deleteEmployeeById(String id, String loggedInUserId) throws Exception {
        if(isAdmin(loggedInUserId) || isSuperAdmin(loggedInUserId)){
            entityManager.createNativeQuery("delete from helpdesk.employee WHERE id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            return "Ticket deleted successfully";
        } else {
            throw new Exception("You are not authorized to Reset Passworf");
        }
    }

    public List<Employee> getAllEngineers(String status, String loggedInUserId) throws Exception {
        if(isAdmin(loggedInUserId) || isSuperAdmin(loggedInUserId)){
            List<Employee> employees = entityManager.createNativeQuery("select id, name, phone,circle,password ,role, status from helpdesk.employee where role = 'Engineer' and status=:status", Employee.class).setParameter("status",status).getResultList();
            employees.forEach(e-> {
                e.setPassword(null);
                e.setRole(null);
            });
            return employees;
        } else {
            throw new Exception("You are not authorized to Reset Passworf");
        }
    }

    public List<Employee> getAllAeit(String status, String loggedInUserId) throws Exception {
        if(isAdmin(loggedInUserId) || isSuperAdmin(loggedInUserId)){
            List<Employee> employees = entityManager.createNativeQuery("select id, name, phone,circle,password ,role, status from helpdesk.employee where role = 'aeit' and status=:status", Employee.class).setParameter("status",status).getResultList();
            employees.forEach(e-> {
                e.setPassword(null);
                e.setRole(null);
            });
            return employees;
        } else {
            throw new Exception("You are not authorized to access Aeit Data");
        }
    }

    public static String getMd5(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public Double getActiveEngineerForAdmin(){
        return ((Number) entityManager.createNativeQuery("select  count(*) from helpdesk.employee where status=:status", Double.class).setParameter("status","Active").getSingleResult()).doubleValue();
    }

    public Boolean isAdmin(String id){
        return employeeJpaRepository.findById(id).getRole().equals("Admin") ? true : false;
    }

    public Boolean isSuperAdmin(String id){
        return employeeJpaRepository.findById(id).getRole().equals("superAdmin") ? true : false;
    }

    public Boolean isAeit(String id){
        return employeeJpaRepository.findById(id).getRole().equals("aeit") ? true : false;
    }

    public Boolean isSurveyor(String id){
        return employeeJpaRepository.findById(id).getRole().equals("Surveyor") ? true : false;
    }

}
