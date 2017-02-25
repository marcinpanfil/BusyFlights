package pl.mpanfil.travix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
    private String departureDate;
    private String returnDate;
    private int numberOfPassengers;

}
