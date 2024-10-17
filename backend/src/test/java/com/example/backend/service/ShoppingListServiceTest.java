package com.example.backend.service;

import com.example.backend.model.Product;
import com.example.backend.model.ShoppingList;
import com.example.backend.repository.ShoppingListRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class ShoppingListServiceTest {

    private final ShoppingListRepository repository = mock(ShoppingListRepository.class);
    private final IdService idService = mock(IdService.class);
    private final ShoppingListService service = new ShoppingListService(idService, repository);


    @Test
    public void getAllLists_shouldReturnAllLists() {
        //GIVEN
        //WHEN
        service.getAllLists();
        //THEN
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getListById_shouldReturnListById() {
        //GIVEN
        Product product1 = new Product("1","product 1");
        ShoppingList list = new ShoppingList("1","List 1", "List 1 description", List.of(product1));
        when(repository.findById("1")).thenReturn(Optional.of(list));
        //WHEN
        ShoppingList result = service.getListById("1");
        //THEN
        assertNotNull(result);
        assertEquals("1", result.id());
        assertEquals("List 1", result.title());
    }
}
