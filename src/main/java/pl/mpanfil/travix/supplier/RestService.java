package pl.mpanfil.travix.supplier;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Marcin Panfil on 26.02.17.
 */
public class RestService<T, K> {

    private RestTemplate restTemplate;
    private String url;

    public RestService(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public List<K> post(T t, ParameterizedTypeReference reference) throws RestServiceException {
        ResponseEntity<List<K>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(t),
                reference
        );
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            String msg = "problem with url: " + url + " status code: " + responseEntity.getStatusCode().value();
            throw new RestServiceException(msg);
        }
    }

}
