package com.axisInfoline.helpDesk.tickets.repository;

import com.axisInfoline.helpDesk.core.domain.Count;
import com.axisInfoline.helpDesk.tickets.domain.Ticket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TicketRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String insertTicket(Ticket ticket){
        entityManager.createNativeQuery("INSERT INTO helpdesk.tickets (" +
                        "id," +
                        "complaint_no," +
                        "complainant_name," +
                        "complainant_designation," +
                        "complainant_contact_no," +
                        "product," +
                        "machine_make," +
                        "problem_type," +
                        "circle," +
                        "division," +
                        "substation," +
                        "landmark," +
                        "pin_code," +
                        "status," +
                        "complaint_datetime," +
                        "uxb1jsi364g4453780," +
                        "project_name) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
                .setParameter(1, ticket.getId())
                .setParameter(2,ticket.getComplaintNo())
                .setParameter(3,ticket.getComplainantName())
                .setParameter(4,ticket.getComplainantDesignation())
                .setParameter(5,ticket.getComplainantContactNo())
                .setParameter(6,ticket.getProduct())
                .setParameter(7,ticket.getMachineMake())
                .setParameter(8,ticket.getProblemType())
                .setParameter(9,ticket.getCircle())
                .setParameter(10,ticket.getDivision())
                .setParameter(11,ticket.getSubstation())
                .setParameter(12,ticket.getLandmark())
                .setParameter(13,ticket.getPinCode())
                .setParameter(14,"OPEN")
                .setParameter(15, ticket.getComplaintDatetime())
                .setParameter(16, ticket.getUxb1jsi364g4453780())
                .setParameter(17, ticket.getProjectName())
                .executeUpdate();
        return ticket.getComplaintNo();
    }

    @Transactional
    public String insertTicketForImport(Ticket ticket){
        entityManager.createNativeQuery("INSERT INTO helpdesk.tickets (" +
                        "id," +
                        "complaint_no," +
                        "complainant_name," +
                        "complainant_designation," +
                        "complainant_contact_no," +
                        "product," +
                        "machine_make," +
                        "problem_type," +
                        "circle," +
                        "division," +
                        "substation," +
                        "landmark," +
                        "pin_code," +
                        "status," +
                        "complaint_datetime," +
                        "defective_item_name," +
                        "uxb1jsi364g4453780," +
                        "engineer_assigned," +
                        "engineer_contact_no," +
                        "action_taken_and_spare_used," +
                        "old_serial_no_mb_hdd_tft," +
                        "new_serial_no_mb_hdd_tft," +
                        "remarks," +
                        "complaint_completion_datetime) " +
                        "VALUES (:id,:complaintNo," +
                        ":complainantName," +
                        ":complainantDesignation," +
                        ":complainantContact_no," +
                        ":product," +
                        ":machineMake," +
                        ":problemType," +
                        ":circle," +
                        ":division," +
                        ":substation," +
                        ":landmark," +
                        ":pinCode," +
                        ":status," +
                        ":complaintDatetime," +
                        ":defectiveItemName," +
                        ":uxb1jsi364g4453780," +
                        ":engineerAssigned," +
                        ":engineerContactNo," +
                        ":actionTakenAndSpareUsed," +
                        ":oldSerialNoMbHddTft," +
                        ":newSerialNoMbHddTft," +
                        ":remarks," +
                        ":complaintCompletionDatetime)")
                .setParameter("id", ticket.getId())
                .setParameter("complaintNo",ticket.getComplaintNo())
                .setParameter("complainantName",ticket.getComplainantName())
                .setParameter("complainantDesignation",ticket.getComplainantDesignation())
                .setParameter("complainantContact_no",ticket.getComplainantContactNo())
                .setParameter("product",ticket.getProduct())
                .setParameter("machineMake",ticket.getMachineMake())
                .setParameter("problemType",ticket.getProblemType())
                .setParameter("circle",ticket.getCircle())
                .setParameter("division",ticket.getDivision())
                .setParameter("substation",ticket.getSubstation())
                .setParameter("landmark",ticket.getLandmark())
                .setParameter("pinCode",ticket.getPinCode())
                .setParameter("status",ticket.getStatus())
                .setParameter("complaintDatetime", ticket.getComplaintDatetime())
                .setParameter("defectiveItemName", ticket.getDefectiveItemName())
                .setParameter("uxb1jsi364g4453780", ticket.getUxb1jsi364g4453780())
                .setParameter("engineerAssigned", ticket.getEngineerAssigned())
                .setParameter("engineerContactNo", ticket.getEngineerContactNo())
                .setParameter("actionTakenAndSpareUsed", ticket.getActionTakenAndSpareUsed())
                .setParameter("oldSerialNoMbHddTft", ticket.getOldSerialNoMbHddTft())
                .setParameter("newSerialNoMbHddTft", ticket.getNewSerialNoMbHddTft())
                .setParameter("remarks", ticket.getRemarks())
                .setParameter("complaintCompletionDatetime", ticket.getComplaintCompletionDatetime())
                .executeUpdate();
        return ticket.getComplaintNo();
    }

    @Transactional
    public String updateTicketByAdmin(Ticket ticket) {
        entityManager.createNativeQuery("UPDATE helpdesk.tickets set circle=:circle, division=:division, complainant_designation=:complainantDesignation, complainant_contact_no=:complainantContactNo, defective_item_name=:defectiveItemName, uxb1jsi364g4453780=:uxb1jsi364g4453780, engineer_assigned=:engineerAssigned, engineer_contact_no=:engineerContactNo, status=:status, action_taken_and_spare_used=:actionTakenAndSpareUsed, old_serial_no_mb_hdd_tft=:oldSerialNoMbHddTft, new_serial_no_mb_hdd_tft=:newSerialNoMbHddTft, remarks=:remarks, project_name=:projectName, product=:product, machine_make=:machineMake, problem_type=:problemType, landmark=:landmark, pin_code=:pinCode, complainant_name=:complainantName, substation=:substation, complaint_datetime=:complaintDatetime, serial_no=:serialNo, complaint_completion_datetime=:complaintCompletionDatetime WHERE serial_no=:serialNo")
        .setParameter("circle", ticket.getCircle())
        .setParameter("division", ticket.getDivision())
        .setParameter("complainantDesignation", ticket.getComplainantDesignation())
        .setParameter("complainantContactNo", ticket.getComplainantContactNo())
        .setParameter("defectiveItemName", ticket.getDefectiveItemName())
        .setParameter("uxb1jsi364g4453780", ticket.getUxb1jsi364g4453780())
        .setParameter("engineerAssigned", ticket.getEngineerAssigned())
        .setParameter("engineerContactNo", ticket.getEngineerContactNo())
        .setParameter("status", ticket.getStatus())
        .setParameter("actionTakenAndSpareUsed", ticket.getActionTakenAndSpareUsed())
        .setParameter("oldSerialNoMbHddTft", ticket.getOldSerialNoMbHddTft())
        .setParameter("newSerialNoMbHddTft", ticket.getNewSerialNoMbHddTft())
        .setParameter("remarks", ticket.getRemarks())
        .setParameter("projectName", ticket.getProjectName())
        .setParameter("product", ticket.getProduct())
        .setParameter("machineMake", ticket.getMachineMake())
        .setParameter("problemType", ticket.getProblemType())
        .setParameter("landmark", ticket.getLandmark())
        .setParameter("pinCode", ticket.getPinCode())
        .setParameter("complainantName", ticket.getComplainantName())
        .setParameter("substation", ticket.getSubstation())
        .setParameter("complaintDatetime", ticket.getComplaintDatetime())
        .setParameter("complaintCompletionDatetime", ticket.getComplaintCompletionDatetime())
        .setParameter("serialNo", ticket.getSerialNo())
                .executeUpdate();
        return "Ticket Successfully updated";
    }

    @Transactional
    public String updateTicketByEngineer(Ticket ticket) {
        entityManager.createNativeQuery("UPDATE helpdesk.tickets set defective_item_name=:defectiveItemName, uxb1jsi364g4453780=:uxb1jsi364g4453780, status=:status, action_taken_and_spare_used=:actionTakenAndSpareUsed, old_serial_no_mb_hdd_tft=:oldSerialNoMbHddTft, new_serial_no_mb_hdd_tft=:newSerialNoMbHddTft,  remarks=:remarks, machine_make=:machineMake, problem_type=:problemType, complaint_completion_datetime=:complaintCompletionDatetime WHERE serial_no=:serialNo")
                .setParameter("defectiveItemName", ticket.getDefectiveItemName())
                .setParameter("uxb1jsi364g4453780", ticket.getUxb1jsi364g4453780())
                .setParameter("status", ticket.getStatus())
                .setParameter("actionTakenAndSpareUsed", ticket.getActionTakenAndSpareUsed())
                .setParameter("oldSerialNoMbHddTft", ticket.getOldSerialNoMbHddTft())
                .setParameter("newSerialNoMbHddTft", ticket.getNewSerialNoMbHddTft())
                .setParameter("remarks", ticket.getRemarks())
                .setParameter("machineMake", ticket.getMachineMake())
                .setParameter("problemType", ticket.getProblemType())
                .setParameter("complaintCompletionDatetime", ticket.getComplaintCompletionDatetime())
                .setParameter("serialNo", ticket.getSerialNo())
                .executeUpdate();
        return "Ticket Successfully updated";
    }

    public List<Ticket> getAllTickets(String status, LocalDateTime fromDate, LocalDateTime toDate){
        return entityManager.createNativeQuery("select * from helpdesk.tickets where status=:status and complaint_datetime between :fromDate and :toDate order by complaint_datetime DESC",Ticket.class).setParameter("status",status).setParameter("fromDate",fromDate).setParameter("toDate",toDate).getResultList();
    }

    public List<Ticket> getTicketsByCircle(String circle, String status, LocalDateTime fromDate, LocalDateTime toDate){
        return entityManager.createNativeQuery("select * from helpdesk.tickets where status=:status and circle= :circle and complaint_datetime between :fromDate and :toDate order by complaint_datetime DESC",Ticket.class).setParameter("status",status).setParameter("circle",circle).setParameter("fromDate",fromDate).setParameter("toDate",toDate).getResultList();
    }

    public List<Count> getTicketsCountMatricesForAdmin(){
        return entityManager.createNativeQuery("select status as name,count(*) as count from helpdesk.tickets group by status", Count.class).getResultList();
    }

    public int getCurrentMonthCreatedTicket(LocalDateTime startDate, LocalDateTime endDate){
        return ((Number) entityManager.createNativeQuery("select  count(*) from helpdesk.tickets where complaint_datetime between :startDate and :endDate", int.class).setParameter("startDate", startDate).setParameter("endDate", endDate).getSingleResult()).intValue();
    }

    public int getCurrentMonthClosedTicket(LocalDateTime startDate, LocalDateTime endDate){
        return ((Number) entityManager.createNativeQuery("select count(*) as count from helpdesk.tickets where complaint_completion_datetime between :startDate and :endDate", int.class).setParameter("startDate", startDate).setParameter("endDate", endDate).getSingleResult()).intValue();
    }

    public List<Ticket> getAllTicketsByPhoneNo(String phone,String status, LocalDateTime fromDate, LocalDateTime toDate){
        return entityManager.createNativeQuery("select * from helpdesk.tickets where status=:status and engineer_contact_no=:phone and complaint_datetime between :fromDate and :toDate order by complaint_datetime DESC",Ticket.class).setParameter("status",status).setParameter("phone",phone).setParameter("fromDate",fromDate).setParameter("toDate",toDate).getResultList();
    }

    @Transactional
    public String deleteTicket(String complaintNumber){
        entityManager.createNativeQuery("delete from helpdesk.tickets WHERE complaint_no=:complaintNumber")
                .setParameter("complaintNumber", complaintNumber)
                .executeUpdate();
        return "Ticket deleted successfully";
    }

}
