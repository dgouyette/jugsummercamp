/**
 * Copyright (C) 2010 Damien GOUYETTE <damien.gouyette@gmail.com>
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cestpasdur.service;

import com.cestpasdur.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
@Transactional
public class ArticleService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);


    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }


    public List<Article> findAll() {
        return em.createQuery("Select a from Article a").getResultList();
    }


    public void remove(Article article) {
        Article articleToRemove = em.merge(article);
        em.remove(articleToRemove);
    }

    @Transactional(readOnly = true)
    public Article findById(Long id) {
        Article article = em.find(Article.class, id);
        return article;
    }


    public void saveOrUpdate(Article article) {
        System.out.println("id "+article.getId());
        if (article.getId() == null) {
            LOGGER.debug("Creation d'un article ");
            em.persist(article);
        } else {
            LOGGER.debug("Mise a jour d'un article : {}", article.getId());
            Article articleToSave = em.merge(article);
            em.persist(articleToSave);
        }


    }
}
