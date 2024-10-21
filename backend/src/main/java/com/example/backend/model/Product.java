package com.example.backend.model;

import lombok.Builder;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@With
@Builder
public record Product(String name, int quantity) {
}
