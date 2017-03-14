package pl.mpanfil.travix

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonSlurper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import pl.mpanfil.travix.searcher.SearchService
import spock.lang.Specification

import java.time.LocalDateTime

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
/**
 * Created by Marcin Panfil on 25.02.17.
 */
class SearchControllerTest extends Specification {

    def searchService = new DummySearchService()
    def underTest = new SearchController(searchService)
    def mockMvc = MockMvcBuilders.standaloneSetup(underTest).build()
    def objectMapper = new ObjectMapper()
    def jsonSlurper = new JsonSlurper()

    def 'returns search results'() {
        given:
        def searchParams = new SearchParams(
                origin: "AMS",
                destination: "LHR",
                departureDate: LocalDateTime.of(2016, 12, 3, 15, 0),
                returnDate: LocalDateTime.of(2016, 12, 15, 14, 0),
                numberOfPassengers: 3
        )
        when:
        def response = mockMvc.perform(post('/search')
                .content(objectMapper.writeValueAsString(searchParams))
                .contentType(APPLICATION_JSON)
        ).andReturn().response
        def content = jsonSlurper.parseText(response.contentAsString)

        then:
        searchService.search(searchParams) >> 1

        response.status == HttpStatus.OK.value()
        response.contentType == MediaType.APPLICATION_JSON_UTF8_VALUE

        content[0].airline == 'best'
        content[1].airline == 'worst'
        content[0].departureAirportCode == 'AMS'
        content[1].departureAirportCode == 'AMS'
        content[0].destinationAirportCode == 'LHR'
        content[1].destinationAirportCode == 'LHR'
        content[0].supplier == 'ToughJet'
        content[1].supplier == 'CrazyAir'
        content[0].fare == 25.00
        content[1].fare == 15.00
    }

    def 'should return 400 bcz of missing origin'() {
        given:
        def searchParams = new SearchParams(
                destination: "LHR",
                departureDate: LocalDateTime.of(2016, 12, 3, 15, 0),
                returnDate: LocalDateTime.of(2016, 12, 15, 14, 0),
                numberOfPassengers: 3
        )
        when:
        def response = mockMvc.perform(post('/search')
                .content(objectMapper.writeValueAsString(searchParams))
                .contentType(APPLICATION_JSON)
        ).andReturn().response

        then:
        response.status == HttpStatus.BAD_REQUEST.value()
    }

    def 'should return 400 bcz of missing return date'() {
        given:
        def searchParams = new SearchParams(
                origin: "AMS",
                destination: "LHR",
                departureDate: LocalDateTime.of(2016, 12, 3, 15, 0),
                numberOfPassengers: 3
        )
        when:
        def response = mockMvc.perform(post('/search')
                .content(objectMapper.writeValueAsString(searchParams))
                .contentType(APPLICATION_JSON)
        ).andReturn().response
        then:
        response.status == HttpStatus.BAD_REQUEST.value()
    }

    def 'should return 400 bcz of wrong number of passangers'() {
        given:
        def searchParams = new SearchParams(
                origin: "AMS",
                destination: "LHR",
                departureDate: LocalDateTime.of(2016, 12, 3, 15, 0),
                returnDate: LocalDateTime.of(2016, 12, 15, 14, 0),
                numberOfPassengers: 8
        )
        when:
        def response = mockMvc.perform(post('/search')
                .content(objectMapper.writeValueAsString(searchParams))
                .contentType(APPLICATION_JSON)
        ).andReturn().response
        then:
        response.status == HttpStatus.BAD_REQUEST.value()
    }

    class DummySearchService implements SearchService {
        @Override
        List<SearchResult> search(SearchParams searchParams) {
            List<SearchResult> searchResults = new ArrayList<>()
            searchResults.add(new SearchResult(
                    airline: "best",
                    departureDate: LocalDateTime.of(2016, 12, 3, 15, 0),
                    arrivalDate: LocalDateTime.of(2016, 12, 13, 15, 0),
                    departureAirportCode: "AMS",
                    destinationAirportCode: "LHR",
                    fare: BigDecimal.valueOf(25.00),
                    supplier: "ToughJet"
            ))
            searchResults.add(new SearchResult(
                    airline: "worst",
                    departureDate: LocalDateTime.of(2016, 12, 3, 15, 0),
                    arrivalDate: LocalDateTime.of(2016, 12, 4, 15, 0),
                    departureAirportCode: "AMS",
                    destinationAirportCode: "LHR",
                    fare: BigDecimal.valueOf(15.00),
                    supplier: "CrazyAir"
            ))
            return searchResults
        }
    }
}
