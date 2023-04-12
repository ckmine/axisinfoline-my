package com.axisInfoline.helpDesk.location.service;

import com.axisInfoline.helpDesk.location.domain.Location;
import com.axisInfoline.helpDesk.location.repository.LocationJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.*;

@Service
public class LocationService {

    @Autowired
    LocationJpaRepository locationJpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<String> getAllCircles(){
        return entityManager.createNativeQuery("select distinct(circle) from helpdesk.location", String.class).getResultList();
    }

    public List<String> getAllDivisions(){
        return entityManager.createNativeQuery("select distinct(division) from helpdesk.location", String.class).getResultList();
    }

    public List<String> getAllDivisionByCircle(String circle){
        return entityManager.createNativeQuery("select distinct(division) from helpdesk.location where circle=:circle", String.class).setParameter("circle",circle).getResultList();
    }

    public List<String> getAllSubDivisionByDivision(String division){
        return entityManager.createNativeQuery("select distinct(subdivision) from helpdesk.location where division=:division", String.class).setParameter("division", division).getResultList();
    }

    public String insertLocation(Location location){
        locationJpaRepository.save(location);
        return "insert location";
    }

    public String importLocation(MultipartFile multipartFile) {
        File file = new File(multipartFile.getOriginalFilename());
        try {
            List<Location> locations = new ArrayList<>();
            XSSFSheet sheet = new XSSFWorkbook(new FileInputStream(file)).getSheetAt(0);
            for(Row row: skipFirst(sheet)){
                Row row1 = row;
                    Location location = new Location();
                    location.setSerialNo((int) row.getCell(0).getNumericCellValue());
                    location.setZone(row.getCell(1).getStringCellValue());
                    location.setCircle(row.getCell(2).getStringCellValue());
                    location.setDivision(row.getCell(3).getStringCellValue());
                    location.setSubdivision(row.getCell(4).getStringCellValue());
                    location.setSubstation(row.getCell(5).getStringCellValue());
                    locations.add(location);
            }
            locationJpaRepository.saveAll(locations);
            System.out.println(locations);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Excel Imported";
    }

    public static <T> Iterable<T> skipFirst(final Iterable<T> c) {
        return new Iterable<T>() {
            @Override public Iterator<T> iterator() {
                Iterator<T> i = c.iterator();
                i.next();
                return i;
            }
        };
    }

    public static final String md5(final String toEncrypt) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(toEncrypt.getBytes());
            final byte[] bytes = digest.digest();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02X", bytes[i]));
            }
            return sb.toString().toLowerCase();
        } catch (Exception exc) {
            return ""; // Impossibru!
        }
    }

    //need to test export functionality
    public String exportLocation() {
        File file  = new File("/home/auriga/Downloads/Read.xlsx");
        try {
            FileInputStream fis = new FileInputStream(file);
            // finds the workbook instance for xlsx file
            XSSFWorkbook myWorkBook =  new XSSFWorkbook(fis);
            // returns the first sheet
            XSSFSheet mysheet = myWorkBook.getSheetAt(0);
            Map<String, Object[]> data = new HashMap<>();
            data.put("1", new Object[]{"User 4",13});
            data.put("2", new Object[]{"User 5",14});
            data.put("3", new Object[]{"USer 6",15});
            Set<String> rows = data.keySet();
            int rownum = mysheet.getLastRowNum();
            for(String key : rows) {
                // Creating a new Row in existing XLSX sheet
                Row row = mysheet.createRow(++rownum);
                Object [] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof String) {
                        cell.setCellValue((String) obj);
                    } else if (obj instanceof Boolean) {
                        cell.setCellValue((Boolean) obj);
                    } else if (obj instanceof Date) {
                        cell.setCellValue((Date) obj);
                    } else if (obj instanceof Integer) {
                        cell.setCellValue((Integer) obj);
                    }
                }
            }
            // open an OutputStream to save written data into XLSX file
            FileOutputStream os = new FileOutputStream(file);
            myWorkBook.write(os);
            System.out.println("Writing on XLSX file Finished ...");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return "File exported";
    }

    public Double getTotalCircles(){
        return ((Number) entityManager.createNativeQuery("select count(distinct(circle)) from helpdesk.location", Double.class).getSingleResult()).doubleValue();
    }

}
