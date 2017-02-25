package pl.mpanfil.travix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.mpanfil.travix.searcher.FlightSearchService;
import pl.mpanfil.travix.searcher.SearchService;
import pl.mpanfil.travix.supplier.CrazyAirService;
import pl.mpanfil.travix.supplier.ToughJetService;

import java.util.Arrays;

@SpringBootApplication
public class BusyFlightsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusyFlightsApplication.class, args);
	}

	@Bean
	SearchService searchService() {
		SearchService flightSearchService = new FlightSearchService();
		flightSearchService.registerFlightServices(Arrays.asList(new CrazyAirService(), new ToughJetService()));
		return flightSearchService;
	}
}
