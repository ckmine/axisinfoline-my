package com.axisInfoline.helpDesk.employee.repository;

import com.axisInfoline.helpDesk.employee.domain.Employee;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeJpaRepository extends org.springframework.data.jpa.repository.JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM helpdesk.employee where status = 'Active' and phone = ?1 AND password = ?2", nativeQuery = true)
    Employee findByPhoneAndPassword(String phone, String password);

    @Query(value = "SELECT * FROM helpdesk.employee where status = 'Active' and id = ?1", nativeQuery = true)
    Employee findById(String id);
}
