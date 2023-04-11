package com.axisInfoline.helpDesk.admin.service;

import com.axisInfoline.helpDesk.core.domain.Count;
import com.axisInfoline.helpDesk.employee.service.EmployeeService;
import com.axisInfoline.helpDesk.location.repository.LocationJpaRepository;
import com.axisInfoline.helpDesk.location.service.LocationService;
import com.axisInfoline.helpDesk.survey.repository.SurveyJpaRepository;
import com.axisInfoline.helpDesk.tickets.repository.TicketRepository;
import com.axisInfoline.helpDesk.tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.axisInfoline.helpDesk.tickets.service.TicketService.currentDateTime;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
public class AdminService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    SurveyJpaRepository surveyJpaRepository;

    @Autowired
    LocationService locationService;

    public Map<String, Double> getCountMatricesForAdmin() {
        Map<String, Double> ticketsCountMatrics = new HashMap<>();
        List<Count> ticketsCountMatricesForAdmin = ticketRepository.getTicketsCountMatricesForAdmin();
        ticketsCountMatricesForAdmin.forEach(data -> {
            ticketsCountMatrics.put(data.getName(),data.getCount());
        });
        ticketsCountMatrics.put("currentMonthCreatedTicket",ticketRepository.getCurrentMonthCreatedTicket(currentDateTime().with(firstDayOfMonth()),currentDateTime().with(lastDayOfMonth())));
        ticketsCountMatrics.put("currentMonthClosedTicket",ticketRepository.getCurrentMonthClosedTicket(currentDateTime().with(firstDayOfMonth()),currentDateTime().with(lastDayOfMonth())));
        ticketsCountMatrics.put("activeEngineers", employeeService.getActiveEngineerForAdmin());
        ticketsCountMatrics.put("totalSurvey", surveyJpaRepository.getAllSurveyCount());
        ticketsCountMatrics.put("totalCircles", locationService.getTotalCircles());
        return ticketsCountMatrics;
    }

}
