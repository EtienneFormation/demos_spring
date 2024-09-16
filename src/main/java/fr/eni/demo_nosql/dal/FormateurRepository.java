package fr.eni.demo_nosql.dal;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.eni.demo_nosql.bo.Formateur;

public interface FormateurRepository extends MongoRepository<Formateur, String> {

}
