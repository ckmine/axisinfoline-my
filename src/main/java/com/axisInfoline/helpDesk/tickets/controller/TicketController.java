package com.axisInfoline.helpDesk.tickets.controller;

import com.axisInfoline.helpDesk.tickets.domain.Ticket;
import com.axisInfoline.helpDesk.tickets.service.TicketService;
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
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/getTickets/admin/{status}")
    public List<Ticket> getTickets(@PathVariable String status) {
        return ticketService.getAllTickets(status);
    }

    @GetMapping("/getTickets/{phone}/{status}")
    public List<Ticket> getTicketsForEngineers(@PathVariable String phone, @PathVariable String status) {
        return ticketService.getAllTicketsByPhoneNo(phone,status);
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
