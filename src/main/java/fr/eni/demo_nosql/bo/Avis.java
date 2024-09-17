package fr.eni.demo_nosql.bo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder

@Document(collection = "reviews")
public class Avis {
	@Id
	private String id;
	
	@Field(name = "pedagogical_note")
	private int notePedagogie;
	
	@Field(name = "pedagogical_commentary")
	private String commentairePedagogie;
	
	@Field(name = "course_note")
	private int noteCours;
	
	@Field(name = "course_commentary")
	private String commentaireCours;
	
	@Field(name = "student")
	private Stagiaire stagiaire;
	
	@DocumentReference // utilisation de la référence par id
	@Field(name = "trainer_id") // facultatif
	private Formateur formateur;
	
	@DBRef // strategie 3 pour clé composite
	@Field(name = "computer_course_id") // facultatif
	private Cours cours;
}











