package com.test1;
import com.test1.reposit.Article;
import org.springframework.web.bind.annotation.*;


@RestController
public class AllControllers {

    ServiceRepositoryLayer articleRepository;

    @GetMapping("/home") // Возвращает список всех Article
    public Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/home/{id}") // Возвращает 1 Article по id
    public Article getOneArticle(@PathVariable int id) {
        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @PostMapping("/home") // сохраняет новый Article и возвращает его
    Article CreateNewArticle(@RequestBody Article newArticle) {
        return articleRepository.save(newArticle);
    }
    @PutMapping("/home/{id}") // Заменяет старый Article новым newArticle по id, если такого id не существует - генерирует новый Article и возвращает его.
    Article replaceArticleIfExist(@RequestBody Article newArticle, @PathVariable int id) {
        if (articleRepository.existsById(id)) {
            Article replacedArticle = articleRepository.findById(id).orElse(new Article());
            replacedArticle.setArticleTitle(newArticle.getArticleTitle());
            replacedArticle.setArticleText(newArticle.getArticleText());
            articleRepository.save(replacedArticle);
            return replacedArticle;
        } else
            return articleRepository.save(newArticle);
    }

    @DeleteMapping("/home/{id}") // Удаляет Article по Id, возвращает удаленный Article
    public Article remove(@PathVariable int id)    {
        Article articleToRemove = articleRepository.findById(id).orElseThrow(RuntimeException::new);

        articleRepository.delete(articleToRemove);
        return articleToRemove;
    }
}