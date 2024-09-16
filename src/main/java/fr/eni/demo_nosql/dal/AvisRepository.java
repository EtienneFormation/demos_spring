package fr.eni.demo_nosql.dal;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.eni.demo_nosql.bo.Avis;

public interface AvisRepository extends MongoRepository<Avis, String> {

}
