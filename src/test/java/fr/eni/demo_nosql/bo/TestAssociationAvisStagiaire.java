package fr.eni.demo_nosql.bo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.demo_nosql.dal.AvisRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestAssociationAvisStagiaire {

	@Autowired
	AvisRepository avisRepository;

	@Test
	void test01_save_avis_stagiaire() {
		final Stagiaire stagiaire = Stagiaire
				.builder()
				.immatriculation("ENI_CAMPUS_202311987")
				.promotion("EDMW0001")
				.build();
		
		final Avis avis = Avis
				.builder()
				.notePedagogie(4)
				.commentairePedagogie("commentaire sur la pédagogie")
				.noteCours(3)
				.commentaireCours("commentaire sur le cours")
				.stagiaire(stagiaire)
				.build();
		
		//Association
		//avis.setStagiaire(stagiaire);
				
		final Avis avisDB = avisRepository.save(avis);
		
		// Vérifier que l'identifiant n'est pas nul
		assertThat(avisDB.getId()).isNotNull();
		assertThat(avisDB.getId()).isNotBlank();

		// Vérifier que la référence embarquée existe
		final Stagiaire stagiaireDB = avisDB.getStagiaire();
		assertThat(stagiaireDB).isNotNull();
		assertThat(stagiaireDB).isEqualTo(stagiaire);
		
		log.info(avisDB.toString());
	}
}
