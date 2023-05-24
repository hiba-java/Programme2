package com.gestionPersonnes.Service;

import com.gestionPersonnes.model.DTO.PersonneDTO;
import com.gestionPersonnes.model.Personne;
import com.gestionPersonnes.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class PersonneService  {

    @Autowired
    private PersonneRepository personneRepository;


    public List<PersonneDTO> getListPersonnesByCritere(String paramNom,String paramPrenom) {
        List<Personne> listFinal = new ArrayList<>();

       if(paramNom!=null || paramPrenom!=null){
           // on cherche les lignes dans la table dont le nom commence ou se termine par le texte paramNom
           List<Personne> list1 =paramNom!=null  ?  personneRepository.findPersonneByNomEndingWithIgnoreCaseOrNomStartingWith(paramNom,paramNom) :new ArrayList<>();
           // on cherche les lignes dans la table dont le prenom commence ou se termine par le texte paramPrenom
           List<Personne> list2 = paramPrenom!=null ? personneRepository.findPersonneByPrenomEndingWithIgnoreCaseOrNomStartingWith(paramPrenom,paramPrenom) : new ArrayList<>();
           listFinal =  Stream.concat(list1.stream(),list2.stream()).distinct().collect(Collectors.toList()); // on fusionne les deux listes et on supprime les doublons s'il y a
       }else {
           listFinal = personneRepository.findAll();// s'il n' y a pas des filtres on affiche toutes les lignes
       }

        return PersonneDTO.listentityToListDTO(listFinal);
    }


    public Optional<Personne> getPersonneById(UUID id) {
        return personneRepository.findById(id); // On recherche tout d'abord la personne dans la base par son id
    }

    public Personne addPersonne(PersonneDTO dto) {
        Personne personne = PersonneDTO.dtoToEntity(dto); // On convertit tout d'abord le dto en entité puis on l'ajoute dans la base
        return personneRepository.save(personne);
    }


    public Personne updatePersonne(PersonneDTO dto) {
         Personne personne = PersonneDTO.dtoToEntity(dto);
         return personneRepository.findById(personne.getId()).map(  // On recherche tout d'abord la personne dans la base et s'il y a on fait la mise à jour
                 p -> {
                     p.setId(personne.getId());
                     p.setPrenom(personne.getPrenom());
                     p.setNom(personne.getNom());
                     return personneRepository.save(p);
                 }).orElseThrow(() -> new RuntimeException("personne non trouvée"));
    }


    public void deletePersonne(UUID id) {

        personneRepository.deleteById(id);
    }
}
