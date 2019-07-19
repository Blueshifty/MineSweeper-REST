package com.example.openshiftDeneme.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.openshiftDeneme.models.Game;

public interface GameRepository extends MongoRepository<Game, String>{

}