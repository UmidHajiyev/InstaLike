package com.umid.instalike.service;


import com.umid.instalike.dto.CreatePostRequest;
import com.umid.instalike.dto.PostResponse;
import com.umid.instalike.entity.Post;
import com.umid.instalike.entity.User;
import com.umid.instalike.repository.PostRepository;
import com.umid.instalike.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        User author = userRepository.findById(request.AuthorId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Author not found"));

        Post post = new Post(request.caption(), author);
        Post savedPost = postRepository.save(post);
        return toResponse(savedPost);
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
