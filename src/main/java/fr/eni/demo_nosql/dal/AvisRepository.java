package fr.eni.demo_nosql.dal;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import fr.eni.demo_nosql.bo.Avis;
import fr.eni.demo_nosql.bo.Cours;
import fr.eni.demo_nosql.bo.Formateur;
import fr.eni.demo_nosql.bo.Stagiaire;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "avis", collectionResourceRel = "avis")
public interface AvisRepository extends MongoRepository<Avis, String> {

	/*
	 * Le @Param est facultatif et permet de renommer les paramètres
	 * dans la requête générée par Spring. Elle n'est utile que pour changer
	 * la trace des requêtes dans les logs.
	 */
	
	// Restitue tous les avis dont la note est égale au paramètre
	List<Avis> findByNoteCours(@Param("noteCours") int noteCours);
	
	// Restitue tous les avis dont la note est supérieure au paramètre
	List<Avis> findByNoteCoursGreaterThan(@Param("noteCours") int noteCours);
	
	// Restitue tous les avis dont la note est inférieure au paramètre
	List<Avis> findByNoteCoursLessThan(@Param("noteCours") int noteCours);
	
	// Tous les avis d'un stagiaire
	List<Avis> findByStagiaireImmatriculation(String immatriculation);
	
	// Tous les avis sur un formateur
	List<Avis> findByFormateurEmail(String email);
	
	// Tous les avis sur un cours
	List<Avis> findByCoursReference(String reference);
}
