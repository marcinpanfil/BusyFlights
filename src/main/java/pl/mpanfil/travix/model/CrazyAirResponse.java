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
public class CrazyAirResponse implements Serializable {

    private String airline;
    private double price;
    private String cabinClass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;

}
