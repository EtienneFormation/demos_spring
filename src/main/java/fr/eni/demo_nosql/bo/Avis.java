package fr.eni.demo_nosql.bo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
}
