package pl.mpanfil.travix.mapper;

import pl.mpanfil.travix.SearchParams;
import pl.mpanfil.travix.SearchResult;
import pl.mpanfil.travix.model.CrazyAirRequest;
import pl.mpanfil.travix.model.CrazyAirResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Marcin Panfil on 20.02.17.
 */
public class CrazyAirEntityMapper implements EntityMapper<CrazyAirRequest, CrazyAirResponse> {

    @Override
    public CrazyAirRequest mapSearchParams(SearchParams searchParams) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return CrazyAirRequest.builder()
                .departureDate(dateTimeFormatter.format(searchParams.getDepartureDate()))
                .returnDate(dateTimeFormatter.format(searchParams.getReturnDate()))
                .destination(searchParams.getDestination())
                .numberOfPassengers(searchParams.getNumberOfPassengers())
                .origin(searchParams.getOrigin())
                .build();
    }

    @Override
    public SearchResult mapSearchResult(CrazyAirResponse crazyAirResponse) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        return SearchResult.builder().airline(crazyAirResponse.getAirline())
                .arrivalDate(LocalDateTime.parse(crazyAirResponse.getArrivalDate(), formatter))
                .departureDate(LocalDateTime.parse(crazyAirResponse.getDepartureDate(), formatter))
                .departureAirportCode(crazyAirResponse.getDepartureAirportCode())
                .destinationAirportCode(crazyAirResponse.getDestinationAirportCode())
                .fare(BigDecimal.valueOf(crazyAirResponse.getPrice()))
                .airline(crazyAirResponse.getAirline())
                .supplier("CRAZY AIR")
                .build();
    }
}
