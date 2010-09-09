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
import com.cestpasdur.entity.Commentaire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Service
@Transactional
public class CommentaireService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CommentaireService.class);

private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }


    public void remove(Commentaire commentaire){

        Commentaire commentaireMerge = em.merge(commentaire);
        em.remove(commentaireMerge);
    }

    public void addCommentaireToArticle(Commentaire currentCommentaire, long idArticle) {
        LOGGER.info("addCommentaireToArticle : idArticle {}, commentaire.email {}", idArticle, currentCommentaire.getEmail());
        Article article = em.find(Article.class, idArticle);
        currentCommentaire.setArticle(article);
        em.persist(currentCommentaire);
    }

    public List<Commentaire> findByArticleId(long idArticle) {
        Query q = em.createQuery("from Commentaire c where  c.article.id =  :idArticle");
        q.setParameter("idArticle",idArticle);
        return q.getResultList();
    }

    
}
