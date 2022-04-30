package com.community.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(Runner.class)
class QuestionServiceTest {

    @Test
    void getPageQuestion() {
        String search="A asdsd sadsda asdasda";
        String[] searchs = search.split(" ");
        String handerSearch="";
        if(searchs!=null){
            handerSearch = Arrays.stream(searchs)
                    .map(s -> {
                        return   s.replace("+", "")
                                .replace("*", "")
                                .replace("?", "");
                    }).collect(Collectors.joining("|"));
        }
        System.out.println(handerSearch);
        Assert.notNull(handerSearch,handerSearch);
    }

    @Test
    void createUpdateQuestion() {
    }

    @Test
    void setQuestionMapper() {
    }
}