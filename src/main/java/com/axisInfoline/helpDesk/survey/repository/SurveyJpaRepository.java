package com.axisInfoline.helpDesk.survey.repository;

import com.axisInfoline.helpDesk.survey.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyJpaRepository extends JpaRepository<Survey, Long> {

    @Query(value = "SELECT * FROM helpdesk.survey where city = ?1", nativeQuery = true)
    public List<Survey> fetchSurveyListByCity(String city);

    @Query(value = "SELECT * FROM helpdesk.survey where circle = ?1", nativeQuery = true)
    public List<Survey> fetchSurveyListByCircle(String circle);

    @Query(value = "SELECT * FROM helpdesk.survey", nativeQuery = true)
    public List<Survey> fetchAllSurveyList();

    @Query(value = "SELECT count(*) FROM helpdesk.survey", nativeQuery = true)
    public Double getAllSurveyCount();

    @Query(value = "INSERT INTO helpdesk.survey(id, city, circle, division, subdivision, end_location_address, it_hardware_name, model, serial_no, ups_battery_status, windows_type, domain_joining_status, utility_contact_person_name, utility_contact_person_contact, machine_make) VALUES (:id, :city, :circle, :division, :subdivision, :end_location_address, :it_hardware_name, :model, :serial_no, :ups_battery_status, :windows_type, :domain_joining_status, :utility_contact_person_name, :utility_contact_person_contact, :machine_make);", nativeQuery = true)
    public String addSurvey();

//    void insertSurveyForImport(Survey survey);
}
