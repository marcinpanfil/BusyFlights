package pl.mpanfil.travix.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Marcin Panfil on 17.02.17.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrazyAirRequest implements Serializable {

    private String origin;
    private String destination;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private LocalDate departureDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private LocalDate returnDate;
    private int numberOfPassengers;

}
