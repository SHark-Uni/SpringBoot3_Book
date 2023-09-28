package me.springboot3.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.springboot3.springbootdeveloper.domain.Article;
import me.springboot3.springbootdeveloper.dto.AddArticleRequestDto;
import me.springboot3.springbootdeveloper.repository.BlogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest  {

    @Autowired
    protected MockMvc mockMvc;

    //Spring Container
    @Autowired
    private WebApplicationContext context;

    @Autowired
    protected ObjectMapper objectMapper;
    //Bean
    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }
    @Test
    @DisplayName("AddArticle: 블로그 글 추가에 성공합니다.")
    void addArticle() throws Exception{
        //given
        final String url = "/api/articles";
        final String title = "TestTitle1";
        final String content = "TestContent1";
        final AddArticleRequestDto userRequest = new AddArticleRequestDto(title,content);

        //객체 ->Json 직렬화.
        final String requestBody = objectMapper.writeValueAsString(userRequest);
        //when
        //HTTP Msg 생성하는 단계인듯? . post는 mockMvcBuilders에 있는친구임. 아마 Get에서도 쓸듯?
        final ResultActions result = mockMvc.perform(
                post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }


}