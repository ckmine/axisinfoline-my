package com.axisInfoline.helpDesk.admin.controller;

import com.axisInfoline.helpDesk.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = {"https://portal.axisinfoline.com","http://localhost:3000"})
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/getDashboard/count/loggedInUserId/{loggedInUserId}")
    public Map<String, Double> getAllCountMatricesForAdmin(@PathVariable String loggedInUserId) throws Exception {
        return adminService.getCountMatricesForAdmin(loggedInUserId);
    }
}
