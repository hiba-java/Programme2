package com.gestionPersonnes;

import com.gestionPersonnes.Service.PersonneService;
import com.gestionPersonnes.model.DTO.PersonneDTO;
import com.gestionPersonnes.model.Personne;
import com.gestionPersonnes.repository.PersonneRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class MyProjectApplicationTests {

	@InjectMocks
	@Spy
	PersonneService personneService;

	@Mock
	PersonneRepository personneRepository;

	@Test
	public void dataSet() {}

	@Test
	void createPesronne(){
		PersonneDTO dto = new PersonneDTO();
		dto.setNom("testNom");
		dto.setPrenom("testPrenom");
		UUID id = UUID.randomUUID();
		dto.setId(id);

		Personne personne = new Personne();
		personne.setNom("testNom");
		personne.setPrenom("testPrenom");
		personne.setId(id);


		doReturn(personne).when(personneRepository).save(personne);
		doReturn(personne).when(personneService).addPersonne(dto);


		Personne pers = personneService.addPersonne(dto);

		assertThat(pers.getNom()).isNotNull().isEqualTo(personne.getNom());
		assertThat(pers.getPrenom()).isNotNull().isEqualTo(personne.getPrenom());

	}
	@Test
	void updatePesronne(){
		PersonneDTO dto = new PersonneDTO();
		dto.setNom("updateNom");
		dto.setPrenom("updatePrenom");
		UUID id = UUID.randomUUID();
		dto.setId(id);

		Personne personne = new Personne();
		personne.setNom("updateNom");
		personne.setPrenom("updatePrenom");
		personne.setId(id);

		doReturn(personne).when(personneRepository).save(personne);
		doReturn(personne).when(personneService).updatePersonne(dto);


		Personne persUp = personneService.updatePersonne(dto);

		assertThat(persUp.getNom()).isNotNull().isEqualTo(personne.getNom());
		assertThat(persUp.getPrenom()).isNotNull().isEqualTo(personne.getPrenom());
		assertThat(persUp.getId()).isNotNull().isEqualTo(personne.getId());

	}
	@Test
	void getPesronneById(){
		UUID id = UUID.randomUUID();
		Personne personne = new Personne();
		personne.setNom("getNom");
		personne.setPrenom("getPrenom");
		personne.setId(id);

		doReturn(Optional.of(personne)).when(personneRepository).findById(id);
		doReturn(Optional.of(personne)).when(personneService).getPersonneById(id);

		Optional<Personne> pers = personneService.getPersonneById(id);

		assertThat(pers.get()).isNotNull();
		assertThat(pers.get().getNom()).isNotNull().isEqualTo(personne.getNom());
		assertThat(pers.get().getPrenom()).isNotNull().isEqualTo(personne.getPrenom());
		assertThat(pers.get().getId()).isNotNull().isEqualTo(personne.getId());
	}
	@Test
	void getListPersonnesByCritere() {
		UUID id1 = UUID.randomUUID();
		UUID id2 = UUID.randomUUID();
		UUID id3 = UUID.randomUUID();

		PersonneDTO personne1 = new PersonneDTO();
		personne1.setNom("getNom");
		personne1.setPrenom("getPrenom");
		personne1.setId(id1);

		PersonneDTO personne2 = new PersonneDTO();
		personne2.setNom("setTest");
		personne2.setPrenom("setTest");
		personne2.setId(id2);

		PersonneDTO personne3 = new PersonneDTO();
		personne3.setNom("mm");
		personne3.setPrenom("pp");
		personne3.setId(id3);

		List<PersonneDTO> listFinal = new ArrayList<>();
		listFinal.add(personne1);
		listFinal.add(personne2);

		List<PersonneDTO> listTotal = new ArrayList<>();
		listTotal.add(personne1);
		listTotal.add(personne2);
		listTotal.add(personne3);

		List<PersonneDTO> listGet = new ArrayList<>();
		listTotal.add(personne1);


		doReturn(listFinal).when(personneService).getListPersonnesByCritere("get","Test");
		doReturn(listTotal).when(personneService).getListPersonnesByCritere(null,null);
		doReturn(listGet).when(personneService).getListPersonnesByCritere("get",null);

		List<PersonneDTO> list = personneService.getListPersonnesByCritere("get", "Test");
		List<PersonneDTO> listSansParams = personneService.getListPersonnesByCritere(null, null);
		List<PersonneDTO> listOneParam = personneService.getListPersonnesByCritere("get", null);

		assertThat(list).isNotNull().isEqualTo(listFinal);
		assertThat(listSansParams).isNotNull().isEqualTo(listTotal);
		assertThat(listOneParam).isNotNull().isEqualTo(listGet);
	}

}
