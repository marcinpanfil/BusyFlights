package pl.mpanfil.travix.mapper;

import pl.mpanfil.travix.SearchParams;
import pl.mpanfil.travix.SearchResult;

/**
 * Created by Marcin Panfil on 20.02.17.
 */
public interface EntityMapper<T, K> {

    T mapSearchParams(SearchParams searchParams);
    SearchResult mapSearchResult(K k);


}
