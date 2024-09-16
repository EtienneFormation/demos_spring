package fr.eni.demo_nosql.bo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

import fr.eni.demo_nosql.dal.FormateurRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestIndex {

	@Autowired
	FormateurRepository formateurRepository;
	
	@Test
	void test01_formateur_OK() {
		final Formateur formateur = Formateur
				.builder()
				.email("abaille@campus-eni.fr")
				.nom("BAILLE")
				.prenom("Anne-Lise")
				.build();
		
		final Formateur formateurDB = formateurRepository.save(formateur);
		
		// Vérifier que l'identifiant n'est pas nul
		assertThat(formateurDB.getEmail()).isNotNull();
		assertThat(formateurDB.getEmail()).isNotBlank();
		assertThat(formateurDB.getEmail()).isEqualTo(formateur.getEmail());
		
		// Vérifier que tous les attributs ne sont pas nuls
		assertThat(formateurDB.getNom()).isNotNull();
		assertThat(formateurDB.getPrenom()).isNotNull();

		log.info(formateurDB.toString());
	}

	@Test
	void test02_unicite_nom() {
		final Formateur formateur = Formateur
				.builder()
				.email("mbaille@campus-eni.fr")
				.nom("BAILLE")
				.prenom("Marcel")
				.build();

		assertThrows(DuplicateKeyException.class, () -> formateurRepository.save(formateur));
	}

	@Test
	void test03_unicite_prenom() {
		final Formateur formateur = Formateur
				.builder()
				.email("atoto@campus-eni.fr")
				.nom("TOTO")
				.prenom("Anne-Lise")
				.build();

		assertThrows(DuplicateKeyException.class, () -> formateurRepository.save(formateur));
	}

@Test
	void test04_non_nul_KO() {
		final Formateur formateur = Formateur
				.builder()
				.email("asupprimer@campus-eni.fr")
				.build();

		final Formateur formateurDB = formateurRepository.save(formateur);
		
		// Vérifier que l'identifiant n'est pas nul
		assertThat(formateurDB.getEmail()).isNotNull();
		assertThat(formateurDB.getEmail()).isNotBlank();



		// Vérifier que les attributs nom et prenom SONT NULS
		assertThat(formateurDB.getNom()).isNull();
		assertThat(formateurDB.getPrenom()).isNull();
		
		log.info(formateurDB.toString());
		
		//Supprimer ce Formateur incorrect
		formateurRepository.delete(formateurDB);
	}
}
