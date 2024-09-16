package fr.eni.demo_nosql.bo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.demo_nosql.dal.CoursRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class TestCoursDocument {

	@Autowired
	CoursRepository coursRepository;

	@Test
	void test_save_cours() {
		CoursId coursId = CoursId
				.builder()
				.reference("M360")
				.filiere("Développement")
				.build();
		
		Cours cours = Cours
				.builder()
				.id(coursId)
				.titre("Java Frameworks - API Web")
				.duree(10)
				.build();
		coursRepository.save(cours);
		

		//Vérification en base
		Optional<Cours> opt = coursRepository.findById(coursId);
		assertThat(opt).isNotNull();
		assertThat(opt.isPresent()).isTrue();
		
		Cours coursDB = opt.get();
		assertThat(coursDB).isNotNull();
		assertThat(coursDB.getTitre()).isNotNull();
		assertThat(coursDB.getDuree()).isEqualTo(10);
		
		log.info(coursDB.toString());
	}
}
