package com.example.backend.controller;

import com.example.backend.model.Product;
import com.example.backend.model.ShoppingList;
import com.example.backend.repository.ShoppingListRepository;
import com.example.backend.service.ShoppingListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ShoppingListControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShoppingListService shoppingListService;
    @MockBean
    private ShoppingListRepository shoppingListRepository;


    @Test
    @DirtiesContext
    void getAllLists() throws Exception {
        Product product1 = new Product("1", "tomato");
        Product product2 = new Product("2", "cheese");
        List<Product> products = List.of(product1, product2);
        ShoppingList shoppingList = new ShoppingList("1", "new 1", "make smth", products);
        when(shoppingListRepository.findAll()).thenReturn(Arrays.asList(shoppingList));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/lists"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [
                                    {
                                        "id": "1",
                                        "title": "new 1",
                                        "description": "make smth",
                                        "products": [
                                            {
                                                "id": "1",
                                                "name": "tomato"
                                            },
                                            {
                                                "id": "2",
                                                "name": "cheese"
                                            }
                                        ]
                                    }
                                ]
                                """
                ));
    }
}