package com.gestionPersonnes.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gestionPersonnes.model.Personne;
import com.sun.istack.NotNull;
import org.springframework.util.Assert;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "nom",
        "prenom"
})
public class PersonneDTO {

    @JsonProperty("id")
    @NotNull
    private UUID id;
    @JsonProperty("nom")
    @NotNull
    private String nom;
    @JsonProperty("prenom")
    @NotNull
    private String prenom;


    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }
    @JsonProperty("nom")
    public void setNom(String nom) {
        this.nom = nom;
    }
    @JsonProperty("prenom")
    public String getPrenom() {
        return prenom;
    }
    @JsonProperty("prenom")
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    //Convertir dto en entite
    public static Personne dtoToEntity(PersonneDTO dto) {
        Assert.notNull(dto, "The given dto must not be null!");
          Personne pers = new Personne();
          pers.setId(dto.getId());
          pers.setNom(dto.getNom());
          pers.setPrenom(dto.getPrenom());

        return pers;
    }
        //Convertir l'entite en dto
    public static PersonneDTO entityToDTO(Personne personne) {
        Assert.notNull(personne, "");
        PersonneDTO dto = new PersonneDTO();
        dto.setId(personne.getId());
        dto.setNom(personne.getNom());
        dto.setPrenom(personne.getPrenom());

        return dto;
    }
    //Convertir  une liste d'entité en un liste de dto
    public static List<PersonneDTO> listentityToListDTO(List<Personne>list){
        Assert.notNull(list, "The given list must not be null!");
        List<PersonneDTO> listDTO = new ArrayList<>();

        list.stream().filter(Objects::nonNull).map(pers->{
            PersonneDTO dto = new PersonneDTO();
            dto.setId(pers.getId());
            dto.setNom(pers.getNom());
            dto.setPrenom(pers.getPrenom());
            listDTO.add(dto);
            return dto;
        }).collect(Collectors.toList());

      return listDTO;

    }
}
