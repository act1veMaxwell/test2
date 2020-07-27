package com.test1.reposit;

import com.test1.Article;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<Article, Integer> {

}
