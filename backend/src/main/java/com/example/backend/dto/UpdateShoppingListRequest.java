package com.example.backend.dto;

import com.example.backend.model.Product;
import java.util.List;

public record UpdateShoppingListRequest(String title, String description, List<Product> products) {
}
