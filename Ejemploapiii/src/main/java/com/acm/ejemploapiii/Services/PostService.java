package com.acm.ejemploapiii.Services;

import com.acm.ejemploapiii.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostService {

    private static final String URL = "https://jsonplaceholder.typicode.com/posts";

    private final RestTemplate restTemplate;

    @Autowired
    public PostService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Post> getPosts() {
        Post[] postsArray = restTemplate.getForObject(URL, Post[].class);
        return List.of(postsArray);
    }

}
