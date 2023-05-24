package com.gestionPersonnes.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="personne")
public class Personne {

    @Id
    @Column(length = 16)
    private UUID id;
    @Column( name = "nom" , nullable = false)
    private String nom;
    @Column( name ="prenom" , nullable = false)
    private String prenom ;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
