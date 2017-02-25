package pl.mpanfil.travix.mapper;


import pl.mpanfil.travix.SearchParams;
import pl.mpanfil.travix.SearchResult;
import pl.mpanfil.travix.model.ToughJetRequest;
import pl.mpanfil.travix.model.ToughJetResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Marcin Panfil on 20.02.17.
 */
public class ToughJetEntityMapper implements EntityMapper<ToughJetRequest, ToughJetResponse> {

    @Override
    public ToughJetRequest mapSearchParams(SearchParams searchParams) {
        return ToughJetRequest.builder()
                .departureDay(searchParams.getDepartureDate().getDayOfMonth())
                .departureMonth(searchParams.getDepartureDate().getMonth().getValue())
                .departureYear(searchParams.getDepartureDate().getYear())
                .returnDay(searchParams.getReturnDate().getDayOfMonth())
                .returnMonth(searchParams.getReturnDate().getMonth().getValue())
                .returnYear(searchParams.getReturnDate().getYear())
                .from(searchParams.getOrigin())
                .to(searchParams.getDestination())
                .numberOfAdults(searchParams.getNumberOfPassengers())
                .build();

    }

    @Override
    public SearchResult mapSearchResult(ToughJetResponse toughJetResponse) {
        return SearchResult.builder()
                .supplier("TOUGH JET")
                .fare(calculatePrize(
                        toughJetResponse.getBasePrice(), toughJetResponse.getTax(), toughJetResponse.getDiscount()))
                .destinationAirportCode(toughJetResponse.getArrivalAirportName())
                .departureAirportCode(toughJetResponse.getDepartureAirportName())
                .airline(toughJetResponse.getCarrier())
                .departureDate(LocalDateTime.of(toughJetResponse.getDepartureYear(),
                        toughJetResponse.getDepartureMonth(),
                        toughJetResponse.getDepartureDay(),
                        0, 0))
                .arrivalDate(LocalDateTime.of(toughJetResponse.getReturnYear(),
                        toughJetResponse.getReturnMonth(),
                        toughJetResponse.getReturnDay(),
                        0, 0))
                .build();
    }

    private BigDecimal calculatePrize(double basePrize, double tax, double discount) {
        BigDecimal prize = BigDecimal.valueOf(basePrize)
                .multiply(BigDecimal.valueOf(1 + (tax / 100)))
                .multiply(BigDecimal.valueOf(1 - discount / 100))
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return prize;
    }

}
