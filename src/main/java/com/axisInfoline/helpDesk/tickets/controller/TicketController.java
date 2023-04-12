package com.axisInfoline.helpDesk.tickets.controller;

import com.axisInfoline.helpDesk.core.domain.Count;
import com.axisInfoline.helpDesk.core.domain.SearchedText;
import com.axisInfoline.helpDesk.tickets.domain.Ticket;
import com.axisInfoline.helpDesk.tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"https://portal.axisinfoline.com","http://localhost:3000"})
@RestController
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/getTickets/admin/{status}/{fromDate}/{toDate}")
    public List<Ticket> getTickets(@PathVariable String status, @PathVariable String fromDate, @PathVariable String toDate) {
        return ticketService.getAllTickets(status, fromDate, toDate);
    }

    @PostMapping("/getTickets/AEIT/{status}/{fromDate}/{toDate}")
    public List<Ticket> getTickets(@RequestBody SearchedText searchedText, @PathVariable String status, @PathVariable String fromDate, @PathVariable String toDate) {
        return ticketService.getTicketsByCircle(searchedText.getText() ,status, fromDate, toDate);
    }

    @GetMapping("/getTickets/{phone}/{status}/{fromDate}/{toDate}")
    public List<Ticket> getTicketsForEngineers(@PathVariable String phone, @PathVariable String status, @PathVariable String fromDate, @PathVariable String toDate) {
        return ticketService.getAllTicketsByPhoneNo(phone,status, fromDate, toDate);
    }

    @PostMapping("/createTicket")
    public String createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    @PatchMapping("/admin/updateTicket")
    public String updateTicket(@RequestBody Ticket ticket) {
        return ticketService.updateTicketByAdmin(ticket);
    }

    @PatchMapping("/engineer/updateTicket")
    public String updateTicketByEngineer(@RequestBody Ticket ticket) {
        return ticketService.updateTicketByEngineer(ticket);
    }

    @DeleteMapping("/deleteTicket/{complaintNumber}")
    public String deleteTicket(@PathVariable String complaintNumber) {
        return ticketService.deleteTicket(complaintNumber);
    }

    @RequestMapping(value = "/importTickets", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> importTickets(@RequestParam("file") MultipartFile multipartFile) {
        try {
            ticketService.importTickets(multipartFile);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
