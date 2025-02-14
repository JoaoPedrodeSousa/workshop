package com.project.workshopmongo.services;

import com.project.workshopmongo.domain.Post;
import com.project.workshopmongo.repository.PostRepository;
import com.project.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post findById(String id){
        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty()){
            throw new ObjectNotFoundException("Objeto nao encontrado");
        }
        return post.get();
    }


}
