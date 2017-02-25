package pl.mpanfil.travix.searcher;

import pl.mpanfil.travix.SearchParams;
import pl.mpanfil.travix.SearchResult;
import pl.mpanfil.travix.supplier.SupplierService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Panfil on 25.02.17.
 */
public class FlightSearchService implements SearchService {

    private List<SupplierService> supplierServices;

    public void registerFlightServices(List<SupplierService> supplierServices) {
        this.supplierServices = supplierServices;
    }

    @Override
    public List<SearchResult> search(SearchParams searchParams) {
        List<SearchResult> searchResults = new ArrayList<>();
        supplierServices.forEach(s -> searchResults.addAll(s.search(searchParams)));
        return searchResults;
    }

}
