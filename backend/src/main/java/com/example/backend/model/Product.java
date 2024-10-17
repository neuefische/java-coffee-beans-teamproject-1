package com.example.backend.model;

import lombok.Builder;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("product")
@With
@Builder
public record Product(String id, String name) {
}
