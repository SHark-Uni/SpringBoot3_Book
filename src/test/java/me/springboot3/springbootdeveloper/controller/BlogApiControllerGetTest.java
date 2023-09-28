package me.springboot3.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.springboot3.springbootdeveloper.domain.Article;
import me.springboot3.springbootdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogApiControllerGetTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    //Mock setup
    @BeforeEach
    void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @DisplayName("findAllArticles: 모든 게시글을 조회합니다.")
    void findAllArticles() throws Exception {
        final String url = "/api/articles";
        final String title = "title1";
        final String content = "content1";

        //
        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        final ResultActions result = mockMvc.perform(
                get(url)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));

    }
}
