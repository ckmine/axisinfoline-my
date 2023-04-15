package com.axisInfoline.helpDesk.survey.service;

import com.axisInfoline.helpDesk.employee.service.EmployeeService;
import com.axisInfoline.helpDesk.survey.domain.Survey;
import com.axisInfoline.helpDesk.survey.repository.SurveyJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class SurveyService {

    @Autowired
    SurveyJpaRepository surveyRepository;

    @Autowired
    EmployeeService employeeService;

    @PersistenceContext
    private EntityManager entityManager;

    public String importSurvey(MultipartFile multipartFile) throws Exception {
        try {
            Long lastSerialNo = getLastSerialNumber();
            List<Survey> surveys = new ArrayList<>();
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(multipartFile.getInputStream());
            for (int i = 0; i < xssfWorkbook.getNumberOfSheets(); i++) {
                //code
                XSSFSheet sheet = xssfWorkbook.getSheetAt(i);
                String sheetName = xssfWorkbook.getSheetAt(i).getSheetName();
                for (Row row : skipFirst(sheet)) {
                    lastSerialNo = lastSerialNo + 1;
                    Row row1 = row;
                    if (row.getCell(1) != null && !StringUtils.isEmpty(row.getCell(1).getStringCellValue())) {
                        Survey survey = new Survey();
                        survey.setId(lastSerialNo);
                        survey.setCity(sheetName);
                        survey.setCircle(row.getCell(1).getStringCellValue());
                        survey.setDivision(row.getCell(2).getStringCellValue());
                        survey.setSubdivision(row.getCell(3).getStringCellValue());
                        survey.setEndLocationAddress(row.getCell(4).getStringCellValue());
                        survey.setItHardwareName(row.getCell(5).getStringCellValue());
                        survey.setMachineMake(row.getCell(6).getStringCellValue());
                        survey.setModel(row.getCell(7).getStringCellValue());
                        survey.setSerialNo(row.getCell(8).getStringCellValue());
                        survey.setUpsBatteryStatus(row.getCell(9).getStringCellValue());
                        survey.setWindowsType(row.getCell(10).getStringCellValue());
                        survey.setDomainJoiningStatus(row.getCell(11).getStringCellValue());
                        survey.setUtilityContactPersonName(row.getCell(12).getStringCellValue());
                        survey.setUtilityContactPersonContact(row.getCell(12).getStringCellValue());
                        surveys.add(survey);
//                        insertSurveyForImport(survey);
                    }
                }
            }
            surveyRepository.saveAll(surveys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Survey imported";
    }

    @Transactional
    public Boolean insertSurveyForImport(Survey survey) {
        entityManager.createNativeQuery("INSERT INTO helpdesk.survey(" +
                        "id," +
                        "city," +
                        "circle," +
                        "division," +
                        "subdivision," +
                        "end_location_address," +
                        "it_hardware_name," +
                        "model," +
                        "serial_no," +
                        "ups_battery_status," +
                        "windows_type," +
                        "domain_joining_status," +
                        "utility_contact_person_name," +
                        "utility_contact_person_contact," +
                        "machine_make) VALUES (" +
                        ":id," +
                        ":city," +
                        ":circle," +
                        ":division," +
                        ":subdivision," +
                        ":end_location_address," +
                        ":it_hardware_name," +
                        ":model," +
                        ":serial_no," +
                        ":ups_battery_status," +
                        ":windows_type," +
                        ":domain_joining_status," +
                        ":utility_contact_person_name," +
                        ":utility_contact_person_contact," +
                        ":machine_make)")
                .setParameter("id", survey.getId())
                .setParameter("city", survey.getCity())
                .setParameter("circle", survey.getCircle())
                .setParameter("division", survey.getDivision())
                .setParameter("end_location_address", survey.getEndLocationAddress())
                .setParameter("it_hardware_name", survey.getItHardwareName())
                .setParameter("model", survey.getModel())
                .setParameter("serial_no", survey.getSerialNo())
                .setParameter("ups_battery_status", survey.getUpsBatteryStatus())
                .setParameter("windows_type", survey.getWindowsType())
                .setParameter("domain_joining_status", survey.getDomainJoiningStatus())
                .setParameter("utility_contact_person_name", survey.getUtilityContactPersonName())
                .setParameter("utility_contact_person_contact", survey.getUtilityContactPersonContact())
                .setParameter("machine_make", survey.getMachineMake())
                .executeUpdate();
        return true;
    }

    public static <T> Iterable<T> skipFirst(final Iterable<T> c) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                Iterator<T> i = c.iterator();
                i.next();
                return i;
            }
        };
    }

    public String saveSurvey(Survey survey) {
        surveyRepository.save(survey);
        return "Survey Added";
    }

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Survey> fetchSurveyList() {
        return surveyRepository.findAll();
    }

    public List<String> getSurveyCities() {
        return entityManager.createNativeQuery("select distinct(city) from helpdesk.survey", String.class).getResultList();
    }

    public List<String> getSurveyCirclesToAddSurveyor() {
        return entityManager.createNativeQuery("select distinct(circle) from helpdesk.survey", String.class).getResultList();
    }

    @Transactional
    public String updateSurvey(Survey survey) {

        surveyRepository.findById(survey.getId())
                .ifPresent(newSurvey -> {
                    newSurvey.setCity(survey.getCity());
                    newSurvey.setCircle(survey.getCircle());
                    newSurvey.setDivision(survey.getDivision());
                    newSurvey.setSubdivision(survey.getSubdivision());
                    newSurvey.setEndLocationAddress(survey.getEndLocationAddress());
                    newSurvey.setItHardwareName(survey.getItHardwareName());
                    newSurvey.setModel(survey.getModel());
                    newSurvey.setSerialNo(survey.getSerialNo());
                    newSurvey.setUpsBatteryStatus(survey.getUpsBatteryStatus());
                    newSurvey.setWindowsType(survey.getWindowsType());
                    newSurvey.setDomainJoiningStatus(survey.getDomainJoiningStatus());
                    newSurvey.setUtilityContactPersonName(survey.getUtilityContactPersonName());
                    newSurvey.setUtilityContactPersonContact(survey.getUtilityContactPersonContact());
                    surveyRepository.save(newSurvey);
                });
        return "Survey Updated";
    }

    public void deleteSurveyById(Long id, String loggedInUserId) throws Exception {
        if (employeeService.isSuperAdmin(loggedInUserId)) {
            surveyRepository.deleteById(id);
        } else {
            throw new Exception("You are not authorized to delete survey");
        }
    }

    public List<Survey> fetchSurveyListByCity(String city) {
        if (!city.equals("All")) {
            return surveyRepository.fetchSurveyListByCity(city);
        } else {
            return surveyRepository.fetchAllSurveyList();
        }
    }

    public List<Survey> fetchSurveyListByCircle(String city) {
        return surveyRepository.fetchSurveyListByCircle(city);
    }

    public ResponseEntity<ByteArrayResource> generateExcelFile(List<Survey> data, String city) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(city);
        int rowCount = 0;
        Row headerRow = sheet.createRow(rowCount++);
        headerRow.createCell(0).setCellValue("City");
        headerRow.createCell(1).setCellValue("Circle");
        headerRow.createCell(2).setCellValue("Division");
        headerRow.createCell(3).setCellValue("Subdivision");
        headerRow.createCell(4).setCellValue("End Location Address");
        headerRow.createCell(5).setCellValue("IT Hardware Name");
        headerRow.createCell(6).setCellValue("Model");
        headerRow.createCell(7).setCellValue("Serial No");
        headerRow.createCell(8).setCellValue("Ups Battery status");
        headerRow.createCell(9).setCellValue("Windows Type");
        headerRow.createCell(10).setCellValue("Domain Joining Status");
        headerRow.createCell(11).setCellValue("Name of Utility Contact Person");
        headerRow.createCell(12).setCellValue("Phone no of Utility Contact Person");

        for (Survey survey : data) {
            Row row = sheet.createRow(rowCount++);

            row.createCell(0).setCellValue(survey.getCity());
            row.createCell(1).setCellValue(survey.getCircle());
            row.createCell(2).setCellValue(survey.getDivision());
            row.createCell(3).setCellValue(survey.getSubdivision());
            row.createCell(4).setCellValue(survey.getEndLocationAddress());
            row.createCell(5).setCellValue(survey.getItHardwareName());
            row.createCell(6).setCellValue(survey.getModel());
            row.createCell(7).setCellValue(survey.getSerialNo());
            row.createCell(8).setCellValue(survey.getUpsBatteryStatus());
            row.createCell(9).setCellValue(survey.getWindowsType());
            row.createCell(10).setCellValue(survey.getDomainJoiningStatus());
            row.createCell(11).setCellValue(survey.getUtilityContactPersonName());
            row.createCell(12).setCellValue(survey.getUtilityContactPersonContact());
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        ByteArrayResource resource = new ByteArrayResource(baos.toByteArray());
        workbook.close();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=survey.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .contentLength(resource.contentLength())
                .body(resource);
    }

    public Long getLastSerialNumber() {
        Query query = entityManager.createNativeQuery("select  max(id) from helpdesk.survey", Long.class);
        return query.getSingleResult() != null ? ((Number) query.getSingleResult()).longValue() : 1;
    }

}
