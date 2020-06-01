package br.gov.sp.fatec.fatec_ipi_noite_chat_com_firebase.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Formatador de datas
 */
public class DateFormater {
    /**
     * Formata a data e hora escondendo a data se for o mesmo dia de que está sendo executada
     *
     * @param date data a ser formatada
     * @return data formatada
     */
    public static String format(Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        final Calendar today = Calendar.getInstance();
        return calendar.get(Calendar.ERA) == today.get(Calendar.ERA) && calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) ? SimpleDateFormat.getTimeInstance().format(date) : SimpleDateFormat.getDateTimeInstance().format(date);
    }
}
