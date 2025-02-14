package com.project.workshopmongo.resource;

import com.project.workshopmongo.domain.Post;
import com.project.workshopmongo.domain.User;
import com.project.workshopmongo.dto.UserDTO;
import com.project.workshopmongo.resource.Utils.URL;
import com.project.workshopmongo.services.PostService;
import com.project.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Post> findbyId(@PathVariable String id){
        Post post = postService.findById(id);

        return ResponseEntity.ok().body(post);
    }

    @RequestMapping(value = "/titlesearch",method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Post> list = postService.findByTitle(text);

        return ResponseEntity.ok().body(list);
    }
}
