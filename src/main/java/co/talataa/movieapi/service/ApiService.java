package co.talataa.movieapi.service;

import co.talataa.movieapi.rest.dto.moviedb.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ApiService {
    @Value("${app.moviedb.apiURL}")
    private String apiBaseURL;

    @Value("${app.moviedb.apiKey}")
    private String apiKey;

    @Value("${app.moviedb.pageSize}")
    private Integer pageSize;

    @Autowired
    private RestTemplate restTemplate;

    public <T> PagedResponse<T> getPage(int pageNumber, String path) {
        String url = String.format("%s/%s?api_key=%s", apiBaseURL, path, apiKey);
        PagedResponse<T> emptyResponse = new PagedResponse<>(pageNumber, List.of(), 0, 0);
        return Optional.of(restTemplate.getForEntity(url, emptyResponse.getClass()))
                .map(response -> (PagedResponse<T>) response.getBody())
                .orElse(emptyResponse);
    }
}
