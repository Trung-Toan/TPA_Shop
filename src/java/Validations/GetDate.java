/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author trantoan
 */
public class GetDate {
    /**
     * methods will get current date
     *
     * @return String current date by format 'yyyy/MM/dd'
     */
    public static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date = LocalDate.now().format(formatter);
        return date;
    }
    
    /**
     * methods will get current date
     *
     * @return Date current date by format 'yyyy/MM/dd'
     */
    public static Date getDate() throws java.text.ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String formattedDate = localDate.format(formatter);
        // Chuyển đổi chuỗi đã định dạng thành đối tượng Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.parse(formattedDate);
    }
}
