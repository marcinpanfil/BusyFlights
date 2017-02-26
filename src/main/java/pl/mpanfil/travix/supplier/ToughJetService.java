package pl.mpanfil.travix.supplier;

import org.springframework.core.ParameterizedTypeReference;
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

    private final EntityMapper entityMapper = new ToughJetEntityMapper();
    private RestService<ToughJetRequest, ToughJetResponse> restService;

    public ToughJetService(RestService<ToughJetRequest, ToughJetResponse> restService) {
        this.restService = restService;
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
