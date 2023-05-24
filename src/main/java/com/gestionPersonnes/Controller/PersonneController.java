package com.gestionPersonnes.Controller;

import com.gestionPersonnes.Service.PersonneService;
import com.gestionPersonnes.model.DTO.PersonneDTO;
import com.gestionPersonnes.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/personne")
public class PersonneController {
    @Autowired
    PersonneService personneService ;

    @PostMapping("/add") // api rest pour ajouter une personne dans la base
    public Personne createPersonne(@RequestBody PersonneDTO dto){
      return personneService.addPersonne(dto) ;
    }

    @GetMapping("/{id}")
    public Optional<Personne> getPersonneById (@PathVariable UUID id){
        return personneService.getPersonneById(id);
    }

    @GetMapping ("/read")// on recherche des personnes dans la base avec deux filtres optionnels (filtre param1 sur le nom et filtre params2 sur le prénom)
    public List<PersonneDTO> getListPersonneByParams(@RequestParam(value = "params1", required = false) String paramsNom,@RequestParam(value = "params2", required = false) String paramsPrenom){
          return personneService.getListPersonnesByCritere(paramsNom,paramsPrenom);
    }

    @PutMapping("/update/{id}") // on update une ligne dans la base
    public Personne updatePersonne(@PathVariable UUID id , @RequestBody PersonneDTO dto){
        return personneService.updatePersonne(dto);
    }

    @DeleteMapping("/delete/{id}") // on supprime un ligne dans la base
    public void deletePersonne (@PathVariable UUID id ){
         personneService.deletePersonne(id);
    }
}
