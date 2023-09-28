package me.springboot3.springbootdeveloper.service;



import lombok.RequiredArgsConstructor;
import me.springboot3.springbootdeveloper.domain.Article;
import me.springboot3.springbootdeveloper.dto.AddArticleRequestDto;
import me.springboot3.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequestDto request){
        return blogRepository.save(request.toEntity());
    }
    public List<Article> findAll(){
        return blogRepository.findAll();
    }
}
