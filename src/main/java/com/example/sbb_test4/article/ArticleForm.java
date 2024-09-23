package com.example.sbb_test4.article;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleForm {

    @Size(max=200)
    private String title;

    private String content;
}
