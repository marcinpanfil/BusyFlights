package pl.mpanfil.travix.searcher;

import pl.mpanfil.travix.SearchParams;
import pl.mpanfil.travix.SearchResult;

import java.util.List;

/**
 * Created by Marcin Panfil on 25.02.17.
 */
public interface SearchService {

    List<SearchResult> search(SearchParams searchParams);
}
