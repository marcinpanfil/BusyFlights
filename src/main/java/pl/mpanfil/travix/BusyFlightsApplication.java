package pl.mpanfil.travix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import pl.mpanfil.travix.logging.LoggingInterceptor;
import pl.mpanfil.travix.searcher.FlightSearchService;
import pl.mpanfil.travix.searcher.SearchService;
import pl.mpanfil.travix.supplier.CrazyAirService;
import pl.mpanfil.travix.supplier.RestService;
import pl.mpanfil.travix.supplier.ToughJetService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"pl.mpanfil.travix"})
public class BusyFlightsApplication {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(BusyFlightsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new LoggingInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    @Bean
    SearchService searchService(RestTemplate restTemplate) {
        SearchService flightSearchService = new FlightSearchService();
        flightSearchService.registerFlightServices(Arrays.asList(
                new CrazyAirService(new RestService<>(restTemplate, environment.getProperty("busyflight.url.crazyair"))),
                new ToughJetService(new RestService<>(restTemplate, environment.getProperty("busyflight.url.toughjet")))
                )
        );
        return flightSearchService;
    }
}
