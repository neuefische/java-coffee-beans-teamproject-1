package com.example.backend.repository;

import com.example.backend.model.ShoppingList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends MongoRepository <ShoppingList, String> {
}
