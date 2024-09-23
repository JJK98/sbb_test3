package com.example.sbb_test4.article;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String articleList(Model model){
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList",articleList);

        return "article_list";
    }

    @GetMapping("/create")
    public String createArticle(ArticleForm articleForm){
        return "article_form";
    }

    @PostMapping("/create")
    public String createArticle(@Valid ArticleForm articleForm, BindingResult bindingResult){
        this.articleService.create(articleForm.getTitle(), articleForm.getContent());
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,@PathVariable("id") Integer id){
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article",article);

        return "article_detail";
    }
    @GetMapping("/modify/{id}")
    public String articleModify(ArticleForm articleForm, @PathVariable("id")Integer id){
        Article article = this.articleService.getArticle(id);
        articleForm.setTitle(article.getTitle());
        articleForm.setContent(article.getContent());

        return "article_form";
    }

    @PostMapping("modify/{id}")
    public String articleModify(@Valid ArticleForm articleForm, BindingResult bindingResult, @PathVariable("id") Integer id){
        Article article = this.articleService.getArticle(id);
        this.articleService.modify(article,articleForm.getTitle(), articleForm.getContent());
        return String.format("redirect:/article/detail/%s", id);
    }

    @GetMapping("/delete/{id}")
    public String articleDelete(@PathVariable("id") Integer id) {
        Article article = this.articleService.getArticle(id);
        this.articleService.delete(article);
        return "redirect:/";
    }
}
