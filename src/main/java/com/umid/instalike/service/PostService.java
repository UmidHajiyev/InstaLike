package com.umid.instalike.service;


import com.umid.instalike.dto.CreatePostRequest;
import com.umid.instalike.dto.PostResponse;
import com.umid.instalike.dto.UpdatePostRequest;
import com.umid.instalike.entity.Post;
import com.umid.instalike.entity.User;
import com.umid.instalike.repository.PostRepository;
import com.umid.instalike.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public PostResponse createPost(CreatePostRequest request)
    {
        User author = userRepository.findById(request.authorId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Author not found"));

        Post post = new Post(request.caption(), author);
        Post savedPost = postRepository.save(post);
        return toResponse(savedPost);
    }

    public List<PostResponse> getAllPosts()
    {
        return postRepository
                .findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }


    public PostResponse getPostById(Long id)
    {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Post not found"));
        return toResponse(post);
    }

    public List<PostResponse> getPostsByAuthor(Long id)
    {
        List<Post> posts = postRepository.findByAuthorId(id);
        if (posts.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No posts found for that user");
        }
        return posts.stream().map(this::toResponse).toList();
    }


    public PostResponse updatePostById(Long id, UpdatePostRequest request)
    {
        Post post = postRepository.findById(id)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND,"Post not found"));

        post.setCaption(request.caption());
        Post updatedPost = postRepository.save(post);
        return toResponse(updatedPost);
    }


    public void deletePostById(Long id)
    {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Post not found"));
        postRepository.deleteById(id);
    }



    private PostResponse toResponse(Post post)
    {
        return new PostResponse(
                post.getId(),
                post.getCaption(),
                post.getCreatedAt(),
                post.getAuthor().getId(),
                post.getAuthor().getUsername()
        );

    }
}
