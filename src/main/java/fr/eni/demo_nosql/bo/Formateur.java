package fr.eni.demo_nosql.bo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder

@Document(collection="trainers")
public class Formateur {
	@Id
	private String email;
	
	@Field(name = "last_name")
	@Indexed(unique = true)
	private String nom;
	
	@Field(name = "first_name")
	@Indexed(unique = true)
	private String prenom;
}









