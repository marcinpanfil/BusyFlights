package pl.mpanfil.travix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import pl.mpanfil.travix.searcher.FlightSearchService;
import pl.mpanfil.travix.searcher.SearchService;
import pl.mpanfil.travix.supplier.CrazyAirService;
import pl.mpanfil.travix.supplier.ToughJetService;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {"pl.mpanfil.travix"})
public class BusyFlightsApplication {

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(BusyFlightsApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	SearchService searchService(RestTemplate restTemplate) {
		SearchService flightSearchService = new FlightSearchService();
		flightSearchService.registerFlightServices(Arrays.asList(
				new CrazyAirService(restTemplate, environment.getProperty("busyflight.url.crazyair")),
				new ToughJetService(restTemplate, environment.getProperty("busyflight.url.toughjet")))
		);
		return flightSearchService;
	}
}
