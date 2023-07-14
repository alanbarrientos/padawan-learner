package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testTimeParsing()
    {
        List<String> dates = Arrays.asList("00:00:01,876", "01:24:59,876");
        for (String date : dates) {
            LocalTime localTime = LocalTime.parse(date, DateTimeFormatter.ofPattern("HH:mm:ss,SSS"));
            int hour = localTime.get(ChronoField.HOUR_OF_DAY);
            int minute = localTime.get(ChronoField.MINUTE_OF_HOUR);
            int second = localTime.get(ChronoField.SECOND_OF_MINUTE);
            int minutoDelDia = localTime.get(ChronoField.MINUTE_OF_DAY);

            System.out.println(date+" hora: "+hour);
            System.out.println(date+" minuto: "+minute);
            System.out.println(date+" segundo: "+second);
            System.out.println(date+" minutoDelDia: "+minutoDelDia);
            System.out.println("");
        }

    }
}
