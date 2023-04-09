package com.axisInfoline.helpDesk.survey.repository;

import com.axisInfoline.helpDesk.survey.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyJpaRepository extends JpaRepository<Survey, Long> {

    @Query(value = "SELECT * FROM helpdesk.survey where city = ?1", nativeQuery = true)
    public List<Survey> fetchSurveyListByCity(String city);

    @Query(value = "SELECT * FROM helpdesk.survey", nativeQuery = true)
    public List<Survey> fetchAllSurveyList();

//    void insertSurveyForImport(Survey survey);
}
