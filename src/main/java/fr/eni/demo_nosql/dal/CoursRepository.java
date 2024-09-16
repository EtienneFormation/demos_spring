package fr.eni.demo_nosql.dal;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.eni.demo_nosql.bo.Cours;
import fr.eni.demo_nosql.bo.CoursId;

public interface CoursRepository extends MongoRepository<Cours, CoursId>{

}
