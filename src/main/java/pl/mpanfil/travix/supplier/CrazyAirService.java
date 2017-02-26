package pl.mpanfil.travix.supplier;

import org.springframework.core.ParameterizedTypeReference;
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

    private final EntityMapper entityMapper = new CrazyAirEntityMapper();
    private RestService<CrazyAirRequest, CrazyAirResponse> restService;

    public CrazyAirService(RestService<CrazyAirRequest, CrazyAirResponse> restService) {
        this.restService = restService;
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
