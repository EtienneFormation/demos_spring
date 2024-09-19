package fr.eni.demo_nosql.dal;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.eni.demo_nosql.bo.Cours;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cours", collectionResourceRel = "cours")
public interface CoursRepository extends MongoRepository<Cours, String>{

}
