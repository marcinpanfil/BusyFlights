package pl.mpanfil.travix.mapper;

import pl.mpanfil.travix.SearchParams;
import pl.mpanfil.travix.SearchResult;
import pl.mpanfil.travix.model.CrazyAirRequest;
import pl.mpanfil.travix.model.CrazyAirResponse;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

/**
 * Created by Marcin Panfil on 20.02.17.
 */
public class CrazyAirEntityMapper implements EntityMapper<CrazyAirRequest, CrazyAirResponse> {

    @Override
    public CrazyAirRequest mapSearchParams(SearchParams searchParams) {
        return CrazyAirRequest.builder()
                .departureDate(searchParams.getDepartureDate().toLocalDate())
                .returnDate(searchParams.getReturnDate().toLocalDate())
                .destination(searchParams.getDestination())
                .numberOfPassengers(searchParams.getNumberOfPassengers())
                .origin(searchParams.getOrigin())
                .build();
    }

    @Override
    public SearchResult mapSearchResult(CrazyAirResponse crazyAirResponse) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        return SearchResult.builder().airline(crazyAirResponse.getAirline())
                .arrivalDate(crazyAirResponse.getArrivalDate())
                .departureDate(crazyAirResponse.getDepartureDate())
                .departureAirportCode(crazyAirResponse.getDepartureAirportCode())
                .destinationAirportCode(crazyAirResponse.getDestinationAirportCode())
                .fare(BigDecimal.valueOf(crazyAirResponse.getPrice()))
                .airline(crazyAirResponse.getAirline())
                .supplier("CRAZY AIR")
                .build();
    }
}
