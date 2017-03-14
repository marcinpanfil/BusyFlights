package pl.mpanfil.travix.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.mpanfil.travix.SearchParams;
import pl.mpanfil.travix.SearchResult;
import pl.mpanfil.travix.mapper.CrazyAirEntityMapper;
import pl.mpanfil.travix.mapper.EntityMapper;
import pl.mpanfil.travix.model.CrazyAirRequest;
import pl.mpanfil.travix.model.CrazyAirResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Panfil on 25.02.17.
 */
@Component
public class CrazyAirService implements SupplierService {

    private final EntityMapper entityMapper = new CrazyAirEntityMapper();
    private RestService<CrazyAirRequest, CrazyAirResponse> restService;

    @Autowired
    public CrazyAirService(RestTemplate restTemplate, Environment environment) {
        this.restService = new RestService<>(restTemplate, environment.getProperty("busyflight.url.crazyair"));
    }

    @Override
    public List<SearchResult> search(SearchParams searchParams) {
        CrazyAirRequest crazyAirRequest = (CrazyAirRequest) entityMapper.mapSearchParams(searchParams);
        List<SearchResult> results = new ArrayList<>();
        try {
            List<CrazyAirResponse> crazyAirResponses =
                    restService.post(crazyAirRequest,
                            new ParameterizedTypeReference<List<CrazyAirResponse>>() {
                            }
                    );
            crazyAirResponses.forEach(crazyAirResponse -> results.add(entityMapper.mapSearchResult(crazyAirResponse)));
        } catch (RestServiceException ex) {
            //TODO: log exception
        }
        return results;
    }
}
