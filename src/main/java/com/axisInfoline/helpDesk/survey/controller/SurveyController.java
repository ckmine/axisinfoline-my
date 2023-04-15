package com.axisInfoline.helpDesk.survey.controller;

import com.axisInfoline.helpDesk.survey.domain.Survey;
import com.axisInfoline.helpDesk.survey.service.SurveyService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"https://portal.axisinfoline.com","http://localhost:3000"})
@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @RequestMapping(value = "/importSurvey", method = RequestMethod.POST)
    public String importSurvey(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        try {
            return surveyService.importSurvey(multipartFile);
        } catch (Exception e) {
            throw new Exception("Incorrect file format");
        }

    }

    @PostMapping("/addSurvey")
    public String saveDepartment(@RequestBody Survey survey) {
        return surveyService.saveSurvey(survey);
    }

    @GetMapping("/getSurvey")
    public List<Survey> getSurveys() {
        return surveyService.fetchSurveyList();
    }

    @GetMapping("/getSurveyByCity/{city}")
    public List<Survey> fetchSurveyListByCity(@PathVariable String city) {
        return surveyService.fetchSurveyListByCity(city);
    }

    @GetMapping("/getSurveyByCircle/{circle}")
    public List<Survey> fetchSurveyListByCircle(@PathVariable String circle) {
        return surveyService.fetchSurveyListByCircle(circle);
    }

    @PatchMapping("/updateSurvey")
    public String updateSurvey(@RequestBody Survey survey) {
        return surveyService.updateSurvey(survey);
    }


    @DeleteMapping("/deleteSurvey/{id}/loggedInUserId/{loggedInUserId}")
    public String deleteSurveyById(@PathVariable("id") Long id, @PathVariable String loggedInUserId) throws Exception {
        surveyService.deleteSurveyById(id, loggedInUserId);
        return "Deleted Successfully";
    }

    @GetMapping("/exportSurvey/{city}")
    public ResponseEntity<ByteArrayResource> exportSurveyByCity(HttpServletResponse response , @PathVariable String city) throws IOException {
        List<Survey> surveys = surveyService.fetchSurveyListByCity(city);
        return surveyService.generateExcelFile(surveys,city);
    }

    @PostMapping("/exportSurveyById")
    public ResponseEntity<ByteArrayResource> exportSurveyById(HttpServletResponse response , @RequestBody List<Integer> ids) throws IOException {
        List<Survey> surveys = surveyService.fetchSurveyListById(ids);
        return surveyService.generateExcelFile(surveys,"Exported-Survey-"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    @GetMapping("/getSurveyCities")
    public List<String> getSurveyCities() {
        return surveyService.getSurveyCities();
    }

    @GetMapping("/getSurveyCirclesToAddSurveyor")
    public List<String> getSurveyCirclesToAddSurveyor() {
        return surveyService.getSurveyCirclesToAddSurveyor();
    }

}
