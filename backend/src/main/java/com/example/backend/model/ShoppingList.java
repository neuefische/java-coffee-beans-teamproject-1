package com.example.backend.model;
import lombok.Builder;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document("shoppingList")
@With
@Builder
public record ShoppingList(String id, String title, String description, List<Product> products) {
}
