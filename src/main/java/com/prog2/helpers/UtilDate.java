package com.prog2.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilDate {

    public static String calendarToStr(Calendar calendar) {
        return (new SimpleDateFormat("yyyy-MM-dd hh:mm a")).format(calendar.getTime());
    }

    public static double getHours(Calendar end, Calendar start) {
        long milisegundos = end.getTimeInMillis()  - start.getTimeInMillis();
        // convertir milisegundos en horas aproximando a la próxima hora superior
        double horas = Math.ceil(milisegundos / 3600000.); // notese el punto en 3600000.
        return horas;
    }

    public static Calendar strToCalendar(String strDate, String format) {
        Calendar fechaHoraInicio = Calendar.getInstance();
        try {  // intentar capturar posibles excepciones (errores)
            fechaHoraInicio.setTime(
                new SimpleDateFormat(format).parse(strDate)
            );
        } catch (ParseException e) {  // capturar el error
            e.printStackTrace();
        }
        return fechaHoraInicio;
    }

    public static String nowToStrNow(String format) {
        return (new SimpleDateFormat(format)).format(new Date());
    }

    public static boolean isOverlap(Calendar star1,Calendar end1,Calendar star2,Calendar end2){
        return (star2.before(end1)) &&  (end2.after(star1));
    }
    
}
