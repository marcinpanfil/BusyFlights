package pl.mpanfil.travix.supplier;

import pl.mpanfil.travix.SearchParams;
import pl.mpanfil.travix.SearchResult;
import pl.mpanfil.travix.mapper.EntityMapper;
import pl.mpanfil.travix.mapper.ToughJetEntityMapper;
import pl.mpanfil.travix.model.ToughJetResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Panfil on 25.02.17.
 */
public class ToughJetService implements SupplierService {

    private EntityMapper entityMapper = new ToughJetEntityMapper();

    @Override
    public List<SearchResult> search(SearchParams searchParams) {
        List<ToughJetResponse> responses = new ArrayList<>();
        List<SearchResult> results = new ArrayList<>();
        responses.stream().forEach(toughJetResponse -> results.add(entityMapper.mapSearchResult(toughJetResponse)));
        return results;
    }
}
