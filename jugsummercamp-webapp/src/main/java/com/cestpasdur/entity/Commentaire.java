package com.cestpasdur.entity;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;


/**
 *
 * @author dgouyette
 */


        @Entity
        public class Commentaire implements Serializable {

            @Email
            @NotEmpty
            private String email;


            @NotEmpty
            @Length(min = 3)
            private String contenu;


    
    

    @OneToOne
    private Article article;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;





    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreated;



     /** GETTER AND SETTER **/


      public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }




    

}
