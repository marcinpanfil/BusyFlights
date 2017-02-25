package pl.mpanfil.travix.supplier;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class CrazyAirService implements SupplierService {

    private EntityMapper entityMapper = new CrazyAirEntityMapper();
    private RestTemplate restTemplate;
    private String crazyAirServiceUrl;

    public CrazyAirService(RestTemplate restTemplate, String crazyAirServiceUrl) {
        this.restTemplate = restTemplate;
        this.crazyAirServiceUrl = crazyAirServiceUrl;
    }

    @Override
    public List<SearchResult> search(SearchParams searchParams) {
        CrazyAirRequest crazyAirRequest = (CrazyAirRequest) entityMapper.mapSearchParams(searchParams);
        List<SearchResult> results = new ArrayList<>();
        ResponseEntity<List<CrazyAirResponse>> responseEntity = restTemplate.exchange(
                crazyAirServiceUrl,
                HttpMethod.POST,
                new HttpEntity<>(crazyAirRequest),
                new ParameterizedTypeReference<List<CrazyAirResponse>>() {
                }
        );
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            List<CrazyAirResponse> result = responseEntity.getBody();
            result.forEach(crazyAirResponse -> results.add(entityMapper.mapSearchResult(crazyAirResponse)));
        }
        return results;
    }
}
