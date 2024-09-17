package fr.eni.demo_nosql.bo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.demo_nosql.dal.AvisRepository;
import fr.eni.demo_nosql.dal.CoursRepository;
import fr.eni.demo_nosql.dal.FormateurRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestRequetes {
	@Autowired
	CoursRepository coursRepository;

	@Autowired
	FormateurRepository formateurRepository;

	@Autowired
	AvisRepository avisRepository;

	void insertion_Formateur_Cours_DB() {
		// Création de Formateur
		final List<Formateur> listeFormateurs = new ArrayList<>();
		listeFormateurs.add(Formateur
				.builder()
				.email("pmontembault@campus-eni.fr")
				.nom("MONTEMBAULT")
				.prenom("Philippe")
				.build());
		listeFormateurs.add(Formateur
				.builder()
				.email("fdelachesnais@campus-eni.frr")
				.nom("DELACHESNAIS")
				.prenom("Frédéric")
				.build());
		// Enregistrement en base
		listeFormateurs.forEach(formateur -> formateurRepository.save(formateur));

		// Création de Cours
		final List<Cours> listeCours = new ArrayList<>();
		listeCours.add(Cours
				.builder()
				.id(CoursId
						.builder()
						.reference("M030")
						.filiere("Développement")
						.build())
				.titre("Web Client")
				.duree(5)
				.build());
		listeCours.add(Cours
				.builder()
				.id(CoursId
						.builder()
						.reference("M070")
						.filiere("Développement")
						.build())
				.titre("POO")
				.duree(10)
				.build());
		// Enregistrement en base
		listeCours.forEach(cours -> coursRepository.save(cours));
	}


	void insertion_Avis_DB() {
		// Récupération depuis la base des Formateur et des Cours
		final List<Formateur> listeFormateurs = formateurRepository.findAll();
		final List<Cours> listeCours = coursRepository.findAll();

		// Ajout d'Avis pour chaque Formateur avec chaque Cours
		for (int i = 0; i < listeFormateurs.size(); i++) {
			// Faire varier la note
			int note = 2;
			final Formateur f = listeFormateurs.get(i);

			for (int j = 0; j < listeCours.size(); j++) {
				final Cours c = listeCours.get(i);
				final Avis avis = Avis
						.builder()
						.notePedagogie(note)
						.commentairePedagogie("Commentaire sur la pédagogie (" + note + ")")
						.noteCours(note)
						.commentaireCours("Commentaire du cours (" + note + ")")
						.cours(c)
						.formateur(f)
						.stagiaire(Stagiaire
								.builder()
								.immatriculation("ENI_1253" + j)
								.promotion("CDA1234" + j)
								.build())
						.build();
				// Sauvegarde de Avis
				avisRepository.save(avis);
				// incrémenter la note
				note++;
			}
		}
	}

	// @Test // NE FAITES JAMAIS CA
	/*
	 * Une methode de test doit servir a tester.
	 */
	@BeforeEach
	void test00_insertion_DB() {
		// On purge la base avant d'insérer de nouvelles données.
		formateurRepository.deleteAll();
		avisRepository.deleteAll();
		coursRepository.deleteAll();
		
		insertion_Formateur_Cours_DB();
		insertion_Avis_DB();
	}

	@Test
	void test01_findByNoteCours_3() {
		List<Avis> listeAvis = avisRepository.findByNoteCours(3);
		assertThat(listeAvis).isNotNull();
		assertThat(listeAvis).isNotEmpty();
		assertThat(listeAvis.size()).isGreaterThan(1);
		log.info("Le nombre d'Avis avec une note = 3 est de : " + listeAvis.size());
	}

	// Test en échec car les données insérées ne génèrent aucune note supérieure à 3
	@Test
	void test02_findByNoteCoursGreaterThan_3() {
		List<Avis> listeAvis = avisRepository.findByNoteCoursGreaterThan(3);
		assertThat(listeAvis).isNotNull();
		assertThat(listeAvis).isNotEmpty();
		assertThat(listeAvis.size()).isGreaterThan(1);
		log.info("Le nombre d'Avis avec une note > 3 est de : " + listeAvis.size());
	}

	@Test
	void test03_findByNoteCoursLessThan_3() {
		List<Avis> listeAvis = avisRepository.findByNoteCoursLessThan(3);
		assertThat(listeAvis).isNotNull();
		assertThat(listeAvis).isNotEmpty();
		assertThat(listeAvis.size()).isGreaterThan(1);
		log.info("Le nombre d'Avis avec une note < 3 est de : " + listeAvis.size());
	}

	@Test
	void test04_findByStagiaire() {
		final Stagiaire stagiaire = Stagiaire
				.builder()
				.immatriculation("ENI_12531")
				.promotion("CDA12341")
				.build();

		List<Avis> listeAvis = avisRepository.findByStagiaire(stagiaire);
		assertThat(listeAvis).isNotNull();
		assertThat(listeAvis).isNotEmpty();
		assertThat(listeAvis.size()).isGreaterThan(1);
		log.info("Nb Avis du Stagiaire (" + stagiaire.toString() + ") : " + listeAvis.size());
		log.info(listeAvis.toString());
	}

	@Test
	void test05_findByFormateur() {
		final Formateur philippe = Formateur
				.builder()
				.email("pmontembault@campus-eni.fr")
				.nom("MONTEMBAULT")
				.prenom("Philippe")
				.build();

		List<Avis> listeAvis = avisRepository.findByFormateur(philippe);
		assertThat(listeAvis).isNotNull();
		assertThat(listeAvis).isNotEmpty();
		assertThat(listeAvis.size()).isGreaterThan(1);
		log.info("Nb Avis sur le Formateur (" + philippe.toString() + ") : " + listeAvis.size());
		log.info(listeAvis.toString());
	}

	@Test
	void test06_findByCours() {
		final Cours cours = Cours
				.builder()
				.id(CoursId
						.builder()
						.reference("M070")
						.filiere("Développement")
						.build())
				.titre("POO")
				.duree(10)
				.build();

		List<Avis> listeAvis = avisRepository.findByCours(cours);
		assertThat(listeAvis).isNotNull();
		assertThat(listeAvis).isNotEmpty();
		assertThat(listeAvis.size()).isGreaterThan(1);
		log.info("Nb Avis sur le Cours (" + cours.toString() + ") : " + listeAvis.size());
		log.info(listeAvis.toString());
	}
}
