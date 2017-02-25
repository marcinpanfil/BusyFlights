package pl.mpanfil.travix.searcher;

import pl.mpanfil.travix.SearchParams;
import pl.mpanfil.travix.SearchResult;
import pl.mpanfil.travix.supplier.SupplierService;

import java.util.List;

/**
 * Created by Marcin Panfil on 25.02.17.
 */
public interface SearchService {

    void registerFlightServices(List<SupplierService> supplierServices);

    List<SearchResult> search(SearchParams searchParams);
}
