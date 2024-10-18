package com.example.backend.dto;

import com.example.backend.model.Product;
import com.example.backend.model.ShoppingList;

import java.util.List;

public record CreateShoppingListRequest(String title, String description, List<Product> products) {

    public ShoppingList toModel() {
        return ShoppingList.builder()
                .title(title)
                .description(description)
                .products(products)
                .build();
    }
}
