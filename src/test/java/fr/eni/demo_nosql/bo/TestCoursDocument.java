package fr.eni.demo_nosql.bo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.demo_nosql.dal.CoursRepository;

@SpringBootTest
class TestCoursDocument {

	@Autowired
	CoursRepository coursRepository;

	@Test
	void test_save_cours() {
		Cours cours = Cours
				.builder()
				.reference("M360")
				.filiere("Développement")
				.titre("Java Frameworks - API Web")
				.duree(10)
				.build();
		coursRepository.save(cours);
		

		//Vérification en base
		Optional<Cours> opt = coursRepository.findById("M360");
		assertThat(opt).isNotNull();
		assertThat(opt.isPresent()).isTrue();
		
		Cours coursDB = opt.get();
		assertThat(coursDB).isNotNull();
		assertThat(coursDB.getTitre()).isNotNull();
		assertThat(coursDB.getDuree()).isEqualTo(10);
		System.out.println(coursDB.toString());
	}
}
