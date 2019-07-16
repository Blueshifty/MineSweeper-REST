package com.example.openshift_deneme.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.openshift_deneme.models.Game;

public interface GameRepository extends MongoRepository<Game, String>{

}