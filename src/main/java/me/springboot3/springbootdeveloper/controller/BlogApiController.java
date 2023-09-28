package me.springboot3.springbootdeveloper.controller;


import lombok.RequiredArgsConstructor;

import me.springboot3.springbootdeveloper.domain.Article;
import me.springboot3.springbootdeveloper.dto.AddArticleRequestDto;
import me.springboot3.springbootdeveloper.dto.ArticleResponseDto;
import me.springboot3.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponseDto>> findAllArticles(){
        List<ArticleResponseDto> articles = blogService.findAll()
                .stream()
                .map(ArticleResponseDto::new)
                .toList();
        return ResponseEntity.ok()
                .body(articles);
    }


    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequestDto request){
        Article savedArticle = blogService.save(request);
        System.out.println("실행되었습니다");
        //성공적으로 자원이 생서되었으며 , 저장된 블로그 글 정보를 응답 객체에 담아 전송 .
        // 201 return
        // 예외처리는 ??  - 네트워크 오류 , 데이터 오류 등
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }
 }
