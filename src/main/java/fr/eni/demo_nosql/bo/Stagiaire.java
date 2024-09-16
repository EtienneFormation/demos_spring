package fr.eni.demo_nosql.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Stagiaire {
	private String immatriculation;
	private String promotion;
}
