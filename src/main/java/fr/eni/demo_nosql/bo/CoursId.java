package fr.eni.demo_nosql.bo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CoursId implements Serializable {
	private static final long serialVersionUID = 1L;

	private String reference;
	private String filiere;
}
