package pl.mpanfil.travix.mapper

import org.springframework.boot.test.context.SpringBootTest
import pl.mpanfil.travix.SearchParams
import pl.mpanfil.travix.model.CrazyAirResponse
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Created by Marcin Panfil on 20.02.17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class CrazyAirEntityMapperTest extends Specification {

    CrazyAirEntityMapper mapper = new CrazyAirEntityMapper();

    def 'should return valid crazy air request'() {
        given:
        def localDateTime = LocalDateTime.of(2016, 12, 10, 10, 0);
        def searchParams = new SearchParams("AMS", "LHR", localDateTime, localDateTime.plusDays(4), 3)
        when:
        def request = mapper.mapSearchParams(searchParams)
        then:
        request.departureDate == LocalDate.of(2016, 12, 10)
        request.returnDate == LocalDate.of(2016, 12, 14)
        request.destination == "LHR"
        request.origin == "AMS"
        request.numberOfPassengers == 3

    }

    def 'should return valid search result'() {
        given:
        def crazyAirResponse = new CrazyAirResponse("The Best One", 200.00, "X", "AMS", "LHR",
                LocalDateTime.of(2016, 2, 12, 15, 34), LocalDateTime.of(2016, 2, 18, 15, 34))
        when:
        def searchResult = mapper.mapSearchResult(crazyAirResponse)
        then:
        searchResult.airline == "The Best One"
        searchResult.departureAirportCode == "AMS"
        searchResult.destinationAirportCode == "LHR"
        searchResult.fare == BigDecimal.valueOf(200.00)
        searchResult.supplier == "CRAZY AIR"
        searchResult.arrivalDate == LocalDateTime.of(2016, 2, 18, 15, 34)
        searchResult.departureDate == LocalDateTime.of(2016, 2, 12, 15, 34)
    }

}
