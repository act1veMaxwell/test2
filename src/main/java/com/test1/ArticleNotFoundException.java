package com.test1;

public class ArticleNotFoundException extends RuntimeException {
    ArticleNotFoundException(int id) {
        super("Could not find article " + id);
    }
}

