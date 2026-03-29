package com.umid.instalike.controller;


import com.umid.instalike.dto.CreatePostRequest;
import com.umid.instalike.dto.PostResponse;
import com.umid.instalike.dto.UpdatePostRequest;
import com.umid.instalike.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@Valid @RequestBody CreatePostRequest request)
    {
        return postService.createPost(request);
    }

    @GetMapping
    public List<PostResponse> getAllPosts()
    {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostResponse getPostbyId(@PathVariable Long id)
    {
        return postService.getPostById(id);

    }

    @GetMapping("/search")
    public List<PostResponse> getPostsByAuthor(@RequestParam Long authorId)
    {
        return postService.getPostsByAuthor(authorId);
    }

    @PutMapping("/{id}")
    public PostResponse updatePostById(@PathVariable Long id, @Valid @RequestBody UpdatePostRequest request)
    {
        return postService.updatePostById(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePostById(@PathVariable Long id)
    {
        postService.deletePostById(id);
    }
}
