package pl.mpanfil.travix.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
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
@Component
public class ToughJetService implements SupplierService {

    private final EntityMapper entityMapper = new ToughJetEntityMapper();
    private RestService<ToughJetRequest, ToughJetResponse> restService;

    @Autowired
    public ToughJetService(RestTemplate restTemplate, Environment environment) {
        this.restService = new RestService<>(restTemplate, environment.getProperty("busyflight.url.toughjet"));
    }

    @Override
    public List<SearchResult> search(SearchParams searchParams) {
        ToughJetRequest toughJetRequest = (ToughJetRequest) entityMapper.mapSearchParams(searchParams);
        List<SearchResult> results = new ArrayList<>();
        try {
            List<ToughJetResponse> toughJetResponses =
                    restService.post(toughJetRequest,
                            new ParameterizedTypeReference<List<ToughJetResponse>>() {
                            }
                    );
            toughJetResponses.forEach(toughJetResponse -> results.add(entityMapper.mapSearchResult(toughJetResponse)));
        } catch (RestServiceException e) {
            //TODO: add some logging
        }
        return results;
    }
}
