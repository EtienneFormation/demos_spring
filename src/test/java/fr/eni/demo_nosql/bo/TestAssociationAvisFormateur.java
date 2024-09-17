package fr.eni.demo_nosql.bo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.demo_nosql.dal.AvisRepository;
import fr.eni.demo_nosql.dal.FormateurRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestAssociationAvisFormateur {

	@Autowired
	AvisRepository avisRepository;

	@Autowired
	FormateurRepository formateurRepository;

	final String emailFormateur = "foireuse@campus-eni.fr";

	@Test
	void test01_save_avis_formateur() {
		final Formateur formateur = Formateur
				.builder()
				.email(emailFormateur)
				.build();



		final Avis avis = Avis
				.builder()
				.notePedagogie(4)
				.commentairePedagogie("commentaire sur la pédagogie")
				.noteCours(3)
				.commentaireCours("commentaire sur le cours")
				.stagiaire(Stagiaire
						.builder()
						.immatriculation("ENI_CAMPUS_202312456")
						.promotion("EDMW0002")
						.build())
				.build();
		
		//Association avec Formateur
		avis.setFormateur(formateur);

		//Sauver
		final Avis avisDB = avisRepository.save(avis);

		// Vérifier que l'identifiant n'est pas nul
		assertThat(avisDB.getId()).isNotNull();
		assertThat(avisDB.getId()).isNotBlank();

		// Vérifier que l'identifiant du Formateur n'est pas nul
		assertThat(avisDB.getFormateur().getEmail()).isNotNull();
		final String emailDB = avisDB.getFormateur().getEmail();
		assertThat(emailDB).isEqualTo(formateur.getEmail());
		log.info(avisDB.toString());
		//Remarquer que seul l'identifiant du Formateur est présent. Les autres attributs sont nuls
		// Pour récupérer les données du Formateur -> il faudrait une seconde requête
	}
	
	@Test
	void test02_recuperer_les_donnees() {
		// 1. Recupérer un avis avec son id
		// id à changer en fonction de si vous testez l'avis avec mail valide ou non
		Optional<Avis> avisOpt = avisRepository.findById("66e936ef724bc979110f9b2b");
		
		// On commence par s'assurer qu'on a un résultat
		if (avisOpt.isPresent()) {
			// Si oui, on extrait l'information du optional
			Avis avis = avisOpt.get();
			
			System.out.println(avis.toString());
			
			
		}
	}
}














