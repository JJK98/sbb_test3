package com.example.sbb_test4.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String createArticle(){
        return "article_form";
    }

    @PostMapping("/create")
    public String createArticle(@RequestParam("title")String title,@RequestParam("content")String content){
        this.articleService.create(title, content);
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,@PathVariable("id") Integer id){
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article",article);

        return "article_detail";
    }
}
