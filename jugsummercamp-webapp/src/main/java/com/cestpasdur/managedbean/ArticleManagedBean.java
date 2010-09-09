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
package com.cestpasdur.managedbean;

import com.cestpasdur.entity.Article;
import com.cestpasdur.entity.Commentaire;
import com.cestpasdur.service.ArticleService;
import com.cestpasdur.service.CommentaireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dgouyette
 */


@ManagedBean
@RequestScoped
public class ArticleManagedBean implements Serializable {

    private final static Logger LOGGER = LoggerFactory.getLogger(ArticleManagedBean.class);


    private Commentaire currentCommentaire = new Commentaire();

    private List<Commentaire> commentaires = new ArrayList<Commentaire>();

    private Article currentArticle;

    private List<Article> articles = new ArrayList<Article>();


    public String doSaveCommentaire() {
        
        commentaireService.addCommentaireToArticle(currentCommentaire, idArticle);
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("success", "Votre commentaire a &eacute;t&eacute; ajout&eacute;");
        currentArticle = new Article();
        return null;
    }


    public String doSaveArticle() {
        articleService.saveOrUpdate(currentArticle);
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("success", "L'objet a ete enregistre avec succes");
        return "index";
    }


    @PostConstruct
    public void init() {
        articles = articleService.findAll();
    }


    private long idArticle;

    @ManagedProperty("#{articleService}")
    private ArticleService articleService;

    @ManagedProperty("#{commentaireService}")
    private CommentaireService commentaireService;


    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public void initParamsView() {
        System.out.println("initParamsView");
        currentArticle = articleService.findById(idArticle);
        commentaires = commentaireService.findByArticleId(idArticle);
    }


    public void refresh() {
        articles = new ArrayList<Article>();
        articles = articleService.findAll();
    }


    public String doRemove(Article article) {
        articleService.remove(article);
        refresh();
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("success", "L'objet a ete supprime avec succes");
        return null;
    }


    public String doRemoveCommentaire(){
     LOGGER.info("doRemoveCommentaire");
        return "";
    }


    public String doRemoveCommentaire(Commentaire commentaire) {
        LOGGER.info("doRemoveCommentaire()");

            commentaireService.remove(commentaire);
            refresh();
            Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            flash.put("success", "Le commentaire a &eacute;t&eacute; supprim&eacute; avec succes");
            return "detail?faces-redirect=true&amp;includeViewParams=true";
        }




    public String doShow(Article article) {
        currentArticle = article;
        return "show?faces-redirect=true&amp;includeViewParams=true";
    }


    /**
     * GETTER AND SETTER *
     */

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }


    public Article getCurrentArticle() {
        return currentArticle;
    }


    public void setCurrentArticle(Article currentArticle) {
        this.currentArticle = currentArticle;
    }


    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }


    public CommentaireService getCommentaireService() {
        return commentaireService;
    }

    public void setCommentaireService(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    public long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(long idArticle) {
        this.idArticle = idArticle;
    }


    public Commentaire getCurrentCommentaire() {
        return currentCommentaire;
    }

    public void setCurrentCommentaire(Commentaire currentCommentaire) {
        this.currentCommentaire = currentCommentaire;
    }
}
