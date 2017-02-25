package pl.mpanfil.travix.supplier;

import pl.mpanfil.travix.SearchParams;
import pl.mpanfil.travix.SearchResult;
import pl.mpanfil.travix.mapper.CrazyAirEntityMapper;
import pl.mpanfil.travix.mapper.EntityMapper;
import pl.mpanfil.travix.model.CrazyAirResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Panfil on 25.02.17.
 */
public class CrazyAirService implements SupplierService {

    private EntityMapper entityMapper = new CrazyAirEntityMapper();

    @Override
    public List<SearchResult> search(SearchParams searchParams) {
        List<CrazyAirResponse> result = new ArrayList<>();
        List<SearchResult> searchResults = new ArrayList<>();
        result.stream().forEach(crazyAirResponse -> searchResults.add(entityMapper.mapSearchResult(crazyAirResponse)));
        return searchResults;
    }
}
