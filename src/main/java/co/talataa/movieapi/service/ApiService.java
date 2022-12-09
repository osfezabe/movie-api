package co.talataa.movieapi.service;

import co.talataa.movieapi.rest.dto.moviedb.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public <T> PagedResponse<T> getPage(String path, int pageNumber) {
        String url = buildRequestURL(path, Map.of("page", String.valueOf(pageNumber)));
        PagedResponse<T> emptyResponse = new PagedResponse<>(pageNumber, List.of(), 0, 0);
        return Optional.of(restTemplate.getForEntity(url, emptyResponse.getClass()))
                .map(response -> (PagedResponse<T>) response.getBody())
                .orElse(emptyResponse);
    }

    public <T> T get(String path, Class<T> clazz) {
        String url = buildRequestURL(path);
        return restTemplate.getForEntity(url, clazz).getBody();
    }

    private String buildRequestURL(String path, Map<String, String> params) {
        String joinedParams = Stream.concat(Map.of("api_key", apiKey).entrySet().stream(), params.entrySet().stream())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        return String.format("%s/%s?%s", apiBaseURL, path, joinedParams);
    }

    private String buildRequestURL(String path) {
        return buildRequestURL(path, Map.of());
    }
}
