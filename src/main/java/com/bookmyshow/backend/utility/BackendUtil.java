package com.bookmyshow.backend.utility;

import com.bookmyshow.backend.model.Seat;
import com.bookmyshow.backend.model.ShowSeat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class BackendUtil {
    public static ShowSeat buildShowSeat(Seat seat, String showId, String status, Integer venueSeatId) {
        ShowSeat showSeat = new ShowSeat();
        showSeat.setPrice(seat.getPrice());
        showSeat.setShowId(showId);
        showSeat.setVenueSeatId(venueSeatId);
        showSeat.setStatus(status);
        return showSeat;
    }

    public static  String getCurrentTimeStamp() {
        return formatTimeStamp(LocalDateTime.now());
    }

    public static String formatTimeStamp(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(localDateTime);
    }

    public static JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getH2DataSource());
    }

    public static DataSource getH2DataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.h2.Driver");
        driverManagerDataSource.setUrl("jdbc:h2:mem:testdb");
        driverManagerDataSource.setUsername("SA");
        driverManagerDataSource.setPassword("");
        return driverManagerDataSource;
    }

    public static boolean isEmpty(String string) {
        if(string == null || string.length()==0) {
            return true;
        } else {
            return false;
        }
    }

    public static String convertToString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0 ; i<=list.size()-1 ; i++) {
            stringBuilder.append(list.get(i));
            if(i!=list.size()-1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }


}
