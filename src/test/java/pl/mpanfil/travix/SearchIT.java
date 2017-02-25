package pl.mpanfil.travix;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mpanfil.travix.searcher.SearchService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marcin Panfil on 25.02.17.
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:application_test.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SearchIT {

    @Autowired
    private SearchService searchService;

    @Test
    public void testSearchWithDummyServiceSuppliers() {
        List<SearchResult> searchResults = searchService.search(SearchParams.builder()
                .departureDate(LocalDateTime.of(2016, 12, 3, 2, 0))
                .returnDate(LocalDateTime.of(2016, 12, 23, 12, 0))
                .destination("AMS")
                .origin("LHR")
                .numberOfPassengers(4)
                .build()
        );
        assertEquals(2, searchResults.size());
    }

}
