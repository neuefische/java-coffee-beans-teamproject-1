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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
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
        Product product1 = new Product("tomato", 1);
        Product product2 = new Product("cheese", 10);
        List<Product> products = List.of(product1, product2);
        ShoppingList shoppingList = new ShoppingList("1", "new 1", "make smth", products);
        when(shoppingListRepository.findAll()).thenReturn(List.of(shoppingList));

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
                                                "name": "tomato",
                                                "quantity": 1
                                            },
                                            {
                                                "name": "cheese",
                                                "quantity": 10
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
        Product product1 = new Product("tomato", 1);
        Product product2 = new Product("cheese", 10);
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
        when(shoppingListRepository.findById("2")).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/lists/2"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("List not found"));
    }

    @Test
    @DirtiesContext
    void save_shouldSaveList() throws Exception {
        Product product1 = new Product("tomato", 1);
        Product product2 = new Product("cheese", 10);
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
                                                            "name": "tomato",
                                                            "quantity": 1
                                                        },
                                                        {
                                                            "name": "cheese",
                                                            "quantity": 10
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
        Product product1 = new Product("tomato", 1);
        Product product2 = new Product("cheese", 10);
        List<Product> products = List.of(product1, product2);
        ShoppingList shoppingList = new ShoppingList("1", "List 1", "Description of List 1", products);

        Product product3 = new Product("milk", 3);
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
                                                            "name": "tomato",
                                                            "quantity": 1
                                                        },
                                                        {
                                                            "name": "milk",
                                                            "quantity": 3
                                                        }
                                        ]
                                    }
                                """)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("Updated List 1"))
                .andExpect(jsonPath("$.description").value("Description of updated List 1"))
                .andExpect(jsonPath("$.products[0].name").value("tomato"))
                .andExpect(jsonPath("$.products[0].quantity").value(1))
                .andExpect(jsonPath("$.products[1].quantity").value(3))
                .andExpect(jsonPath("$.products[1].name").value("milk"));
    }

    @Test
    @DirtiesContext
    void deleteShoppingList_shouldDeleteList() throws Exception {
        Product product1 = new Product("tomato", 1);
        Product product2 = new Product("cheese", 10);
        List<Product> products = List.of(product1, product2);
        ShoppingList shoppingList = new ShoppingList("1", "List to Delete", "Description to Delete", products);

        when(shoppingListRepository.findById("1")).thenReturn(Optional.of(shoppingList));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/lists/1"))
                .andExpect(status().isOk()); // Проверяем успешный статус
        verify(shoppingListRepository).deleteById("1");
    }
}