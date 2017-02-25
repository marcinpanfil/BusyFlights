package pl.mpanfil.travix.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Marcin Panfil on 16.02.17.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrazyAirResponse implements Serializable {

    private String airline;
    private double price;
    private String cabinClass;
    private String departureAirportCode;
    private String destinationAirportCode;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime departureDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime arrivalDate;

}
