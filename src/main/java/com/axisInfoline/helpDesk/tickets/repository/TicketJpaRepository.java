package com.axisInfoline.helpDesk.tickets.repository;

import com.axisInfoline.helpDesk.tickets.domain.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketJpaRepository extends org.springframework.data.jpa.repository.JpaRepository<Ticket, Long> {

}
