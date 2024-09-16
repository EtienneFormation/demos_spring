package fr.eni.demo_nosql.bo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder

@Document(collection = "computer_course")
public class Cours {
	@Id
	private CoursId id;
	private String titre;
	private int duree;
}
