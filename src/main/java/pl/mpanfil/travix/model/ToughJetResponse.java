package pl.mpanfil.travix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Marcin Panfil on 16.02.17.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToughJetResponse implements Serializable {

    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private int departureDay;
    private int departureMonth;
    private int departureYear;
    private int returnDay;
    private int returnMonth;
    private int returnYear;

}
