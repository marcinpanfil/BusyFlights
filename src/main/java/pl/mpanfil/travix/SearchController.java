package pl.mpanfil.travix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.mpanfil.travix.searcher.SearchService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Marcin Panfil on 25.02.17.
 */

@RestController
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<SearchResult>> search(@RequestBody @Valid SearchParams searchParams) {
        return ResponseEntity.ok(searchService.search(searchParams));
    }

}
