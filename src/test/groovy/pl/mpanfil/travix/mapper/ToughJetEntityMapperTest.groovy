package pl.mpanfil.travix.mapper

import org.springframework.boot.test.context.SpringBootTest
import pl.mpanfil.travix.SearchParams
import pl.mpanfil.travix.model.ToughJetResponse
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Created by Marcin Panfil on 20.02.17.
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ToughJetEntityMapperTest extends Specification {

    ToughJetEntityMapper mapper = new ToughJetEntityMapper();

    def 'should return valid tough jet request'() {
        given:
        def localDateTime = LocalDateTime.of(2016, 12, 10, 10, 0);
        def searchParams = new SearchParams("AMS", "LHR", localDateTime, localDateTime.plusDays(4), 3)
        when:
        def request = mapper.mapSearchParams(searchParams)
        then:
        request.departureDay == 10
        request.departureMonth == 12
        request.departureYear == 2016
        request.returnDay == 14
        request.returnMonth == 12
        request.returnYear == 2016
        request.from == "AMS"
        request.to == "LHR"
        request.numberOfAdults == 3
    }

    def 'should return valid search result'() {
        given:
        def toughJetResponse = new ToughJetResponse("Mega Boom", 25.14, 10.5, 8.3, "WAW", "MAL", 2, 3, 2017, 7, 3, 2017)
        when:
        def result = mapper.mapSearchResult(toughJetResponse)
        then:
        result.airline == "Mega Boom"
        result.arrivalDate == LocalDateTime.of(2017, 3, 7, 0, 0)
        result.departureDate == LocalDateTime.of(2017, 3, 2, 0, 0)
        result.departureAirportCode == "WAW"
        result.destinationAirportCode == "MAL"
        result.supplier == "TOUGH JET"
        result.fare == 25.47
    }

}
