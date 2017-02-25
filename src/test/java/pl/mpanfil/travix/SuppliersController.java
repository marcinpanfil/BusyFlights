package pl.mpanfil.travix;

import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.mpanfil.travix.model.CrazyAirRequest;
import pl.mpanfil.travix.model.CrazyAirResponse;
import pl.mpanfil.travix.model.ToughJetRequest;
import pl.mpanfil.travix.model.ToughJetResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Panfil on 25.02.17.
 */
@TestComponent
@RestController
public class SuppliersController {

    @RequestMapping(value = "/tough", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ToughJetResponse>> toughJet(@RequestBody ToughJetRequest request) {
        List<ToughJetResponse> jetResponses = new ArrayList<>();
        jetResponses.add(ToughJetResponse.builder()
                .departureDay(1).departureMonth(1).departureYear(2016)
                .returnDay(2).returnMonth(2).returnYear(2016)
                .arrivalAirportName("AMS")
                .departureAirportName("LHR")
                .carrier("boom!")
                .basePrice(25.45).discount(10.00).tax(5)
                .build()
        );
        return ResponseEntity.ok(jetResponses);
    }


    @RequestMapping(value = "/crazy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CrazyAirResponse>> crazyAir(@RequestBody CrazyAirRequest request) {
        List<CrazyAirResponse> responses = new ArrayList<>();
        responses.add(CrazyAirResponse.builder()
                .departureDate("12-03-2016 15:30:00")
                .arrivalDate("12-23-2016 11:30:00")
                .cabinClass("B")
                .departureAirportCode("AMS")
                .destinationAirportCode("LHR")
                .price(25.45)
                .airline("ups")
                .build()
        );
        return ResponseEntity.ok(responses);
    }
}
