package com.project.workshopmongo.config;

import com.project.workshopmongo.domain.Post;
import com.project.workshopmongo.domain.User;
import com.project.workshopmongo.dto.AuthorDTO;
import com.project.workshopmongo.dto.CommentDTO;
import com.project.workshopmongo.repository.PostRepository;
import com.project.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.xml.stream.events.Comment;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Brown", "alex@gmail.com");
        User bob = new User(null, "Bob Brown", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post = new Post(null,sdf.parse("21/03/2018"), "Partiu viagem", "vou viajar para São Paulo. Abraços",new AuthorDTO(maria));
        Post post2 = new Post(null,sdf.parse("21/03/2018"), "Bom dia", "Acordei feliz hoje",new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO c2= new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("boa viagem mano, tenha um bom dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));

        post.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post, post2));

        maria.getPosts().addAll(Arrays.asList(post, post2));
        userRepository.save(maria);
    }
}
