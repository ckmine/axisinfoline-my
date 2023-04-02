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
import java.util.List;

@CrossOrigin(origins = {"https://portal.axisinfoline.com","http://localhost:3000"})
@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @RequestMapping(value = "/importSurvey", method = RequestMethod.POST)
    public ResponseEntity<String> survey(@RequestParam("file") MultipartFile multipartFile) {
        try {
            surveyService.importSurvey(multipartFile);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

    @PatchMapping("/updateSurvey")
    public String updateSurvey(@RequestBody Survey survey) {
        return surveyService.updateSurvey(survey);
    }


    @DeleteMapping("/deleteSurvey/{id}")
    public String deleteSurveyById(@PathVariable("id") Long id) {
        surveyService.deleteSurveyById(id);
        return "Deleted Successfully";
    }

    @GetMapping("/exportSurvey/{city}")
    public ResponseEntity<ByteArrayResource> exportSurveyByCity(HttpServletResponse response , @PathVariable String city) throws IOException {
        List<Survey> surveys = surveyService.fetchSurveyListByCity(city);
        return surveyService.generateExcelFile(surveys,city);
    }

    @GetMapping("/getSurveyCities")
    public List<String> getSurveyCities() {
        return surveyService.getSurveyCities();
    }

}
