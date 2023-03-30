package com.axisInfoline.helpDesk.location.controller;

import com.axisInfoline.helpDesk.location.domain.Location;
import com.axisInfoline.helpDesk.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//for local
@CrossOrigin(origins = {"http://localhost:3000"})
//for server
//@CrossOrigin(origins = {"https://portal.axisinfoline.com"})
@RestController
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping(value = "/getAllCircles")
        public List<String> getAllCircles(){
        return locationService.getAllCircles();
    }

    @GetMapping(value = "/getAllDivisions")
    public List<String> getAllDivisions(){
        return locationService.getAllDivisions();
    }

    @GetMapping(value = "/getAllDivisionByCircle/{circle}")
    public List<String> getAllDivisionByCircle(@PathVariable String circle){
        return locationService.getAllDivisionByCircle(circle);
    }

    @PostMapping(value = "/insertLocation")
    public String insertLocation(Location location){
        return locationService.insertLocation(location);
    }

    @RequestMapping(value = "/importLocation", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        try {
            locationService.importLocation(multipartFile);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
