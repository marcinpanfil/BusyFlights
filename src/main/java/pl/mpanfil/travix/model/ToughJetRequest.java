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
public class ToughJetRequest implements Serializable {

    private String from;
    private String to;
    private int departureDay; //Day of the Month
    private int departureMonth; //Month as an Integer(1-12)
    private int departureYear; // 4 digit Year
    private int returnDay; //Day of the Month
    private int returnMonth; //Month as an Integer(1-12)
    private int returnYear; //4 digit Year
    private int numberOfAdults; // Number of passengers


}
