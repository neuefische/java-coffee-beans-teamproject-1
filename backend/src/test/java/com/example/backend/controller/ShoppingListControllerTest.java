package com.example.backend.controller;

import com.example.backend.model.Product;
import com.example.backend.model.ShoppingList;
import com.example.backend.repository.ShoppingListRepository;
import com.example.backend.service.IdService;
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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ShoppingListControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShoppingListService shoppingListService;
    @MockBean
    private ShoppingListRepository shoppingListRepository;
    @MockBean
    private IdService idService;


    @Test
    @DirtiesContext
    void getAllLists_shouldReturnAllLists() throws Exception {
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

    @Test
    @DirtiesContext
    void getListById_shouldReturnListById() throws Exception {
        Product product1 = new Product("1", "tomato");
        Product product2 = new Product("2", "cheese");
        List<Product> products = List.of(product1, product2);
        ShoppingList shoppingList = new ShoppingList("1", "List 1", "Description of List 1", products);
        when(shoppingListRepository.findById("1")).thenReturn(Optional.of(shoppingList));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lists/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.description").value("Description of List 1"));
    }

    @Test
    @DirtiesContext
    void getListById_shouldReturnNotFoundIfListDoesNotExist() throws Exception {
        when(shoppingListService.getListById("2")).thenThrow(new NoSuchElementException("List not found"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lists/2"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("List not found"));
    }

    @Test
    @DirtiesContext
    void save_shouldSaveList() throws Exception {
        Product product1 = new Product("1", "tomato");
        Product product2 = new Product("2", "cheese");
        List<Product> products = List.of(product1, product2);
        ShoppingList shoppingList = new ShoppingList("2", "List 2", "Description of List 2", products);
        when(idService.randomId()).thenReturn("2");
        when(shoppingListRepository.save(shoppingList)).thenReturn(shoppingList);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/lists")
                        .content("""
                        {
                            "title": "List 2",
                            "description": "Description of List 2",
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
                    """)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.description").value("Description of List 2"));
    }

    @Test
    @DirtiesContext
    void updateShoppingList_shouldUpdateShoppingList() throws Exception {
        Product product1 = new Product("1", "tomato");
        Product product2 = new Product("2", "cheese");
        List<Product> products = List.of(product1, product2);
        ShoppingList shoppingList = new ShoppingList("1", "List 1", "Description of List 1", products);

        Product product3 = new Product("3", "milk");
        List<Product> updatedProductList = List.of(product1, product3);
        ShoppingList updatedShoppingList = new ShoppingList("1", "Updated List 1", "Description of updated List 1", updatedProductList);
        when(shoppingListRepository.findById("1")).thenReturn(Optional.of(shoppingList));
        when(shoppingListRepository.save(updatedShoppingList)).thenReturn(updatedShoppingList);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/lists/1")
                        .content("""
                        {
                            "title": "Updated List 1",
                            "description": "Description of updated List 1",
                            "products": [
                                {
                                    "id": "1",
                                    "name": "tomato"
                                },
                                {
                                    "id": "3",
                                    "name": "milk"
                                }
                            ]
                        }
                    """)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("Updated List 1"))
                .andExpect(jsonPath("$.description").value("Description of updated List 1"))
                .andExpect(jsonPath("$.products[0].id").value("1"))
                .andExpect(jsonPath("$.products[0].name").value("tomato"))
                .andExpect(jsonPath("$.products[1].id").value("3"))
                .andExpect(jsonPath("$.products[1].name").value("milk"));
    }

    @Test
    @DirtiesContext
    void deleteShoppingList_shouldDeleteList() throws Exception {
        Product product1 = new Product("1", "tomato");
        Product product2 = new Product("2", "cheese");
        List<Product> products = List.of(product1, product2);
        ShoppingList shoppingList = new ShoppingList("1", "List to Delete", "Description to Delete", products);

        when(shoppingListRepository.findById("1")).thenReturn(Optional.of(shoppingList));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/lists/1"))
                .andExpect(status().isOk()); // Проверяем успешный статус
        verify(shoppingListRepository).deleteById("1");
    }
}