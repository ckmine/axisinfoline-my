package com.axisInfoline.helpDesk.tickets.repository;

import com.axisInfoline.helpDesk.survey.domain.Survey;
import com.axisInfoline.helpDesk.tickets.domain.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketJpaRepository extends org.springframework.data.jpa.repository.JpaRepository<Ticket, Long> {

    @Query(value = "SELECT * FROM helpdesk.tickets where complaint_no in ?1", nativeQuery = true)
    public List<Ticket> fetchSurveyListById(List<String> complaintNoList);

}
