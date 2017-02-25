package pl.mpanfil.travix.supplier;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.mpanfil.travix.SearchParams;
import pl.mpanfil.travix.SearchResult;
import pl.mpanfil.travix.mapper.EntityMapper;
import pl.mpanfil.travix.mapper.ToughJetEntityMapper;
import pl.mpanfil.travix.model.ToughJetRequest;
import pl.mpanfil.travix.model.ToughJetResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Panfil on 25.02.17.
 */
public class ToughJetService implements SupplierService {

    private EntityMapper entityMapper = new ToughJetEntityMapper();
    private RestTemplate restTemplate;
    private String toughJetServiceUrl;

    public ToughJetService(RestTemplate restTemplate, String toughJetServiceUrl) {
        this.restTemplate = restTemplate;
        this.toughJetServiceUrl = toughJetServiceUrl;
    }

    @Override
    public List<SearchResult> search(SearchParams searchParams) {
        ToughJetRequest toughJetRequest = (ToughJetRequest) entityMapper.mapSearchParams(searchParams);
        List<SearchResult> results = new ArrayList<>();
        ResponseEntity<List<ToughJetResponse>> responseEntity = restTemplate.exchange(
                toughJetServiceUrl,
                HttpMethod.POST,
                new HttpEntity<>(toughJetRequest),
                new ParameterizedTypeReference<List<ToughJetResponse>>() {
                }
        );
        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            List<ToughJetResponse> responses = responseEntity.getBody();
            responses.stream().forEach(toughJetResponse -> results.add(entityMapper.mapSearchResult(toughJetResponse)));
        }
        return results;
    }
}
