package com.test1;

import com.test1.reposit.Article;
import com.test1.reposit.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServiceRepositoryLayer implements ArticleRepository {
    @Autowired
    ArticleRepository articleRepository;

    @Override   // Только этиот метод имеет логику проверки. Все остальные - просто передают контроль в ArticleRepositoty
    public <S extends Article> S save(S s) {
        if (s != null && s instanceof Article)
            return articleRepository.save(s);
        else
            return null;
    }

    @Override
    public <S extends Article> Iterable<S> saveAll(Iterable<S> iterable) {
        return articleRepository.saveAll(iterable);
    }

    @Override
    public Optional<Article> findById(Integer integer) {
        return articleRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return articleRepository.existsById(integer);
    }

    @Override
    public Iterable<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Iterable<Article> findAllById(Iterable<Integer> iterable) {
        return articleRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return articleRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        articleRepository.deleteById(integer);
    }

    @Override
    public void delete(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public void deleteAll(Iterable<? extends Article> iterable) {
        articleRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        articleRepository.deleteAll();
    }
}
