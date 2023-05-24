package com.gestionPersonnes.repository;

import com.gestionPersonnes.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, UUID> {


   List<Personne> findPersonneByNomEndingWithIgnoreCaseOrNomStartingWith(String str1,String str2);

   List<Personne> findPersonneByPrenomEndingWithIgnoreCaseOrNomStartingWith(String str1,String str2);

}
