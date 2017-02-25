package pl.mpanfil.travix.supplier;

import pl.mpanfil.travix.SearchParams;
import pl.mpanfil.travix.SearchResult;

import java.util.List;

/**
 * Created by Marcin Panfil on 25.02.17.
 */
public interface SupplierService {

    List<SearchResult> search(SearchParams searchParams);

}
