package com.example.backend.service;

import com.example.backend.dto.UpdateShoppingListRequest;
import com.example.backend.model.Product;
import com.example.backend.model.ShoppingList;
import com.example.backend.repository.ShoppingListRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
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
        Product product1 = new Product("product 1",1);
        ShoppingList shoppingList = new ShoppingList("1","List 1", "List 1 description", List.of(product1));
        when(repository.findById("1")).thenReturn(Optional.of(shoppingList));
        //WHEN
        ShoppingList result = service.getListById("1");
        //THEN
        assertNotNull(result);
        assertEquals("1", result.id());
        assertEquals("List 1", result.title());
    }

    @Test
    public void getListById_shouldReturnNullIfNotFound() {
        // GIVEN
        when(repository.findById("2")).thenReturn(Optional.empty());
        // WHEN
        ShoppingList result = service.getListById("2");
        // THEN
        assertNull(result);
    }

    @Test
    public void createList_shouldReturnListById() {
        //GIVEN
        Product product1 = new Product("product 1",1);
        ShoppingList shoppingList = new ShoppingList("1","List 1", "List 1 description", List.of(product1));
        when(idService.randomId()).thenReturn("1");
        //WHEN
        service.createShoppingList(shoppingList);
        //THEN
        verify(repository, times(1)).save(shoppingList);
    }

    @Test
    public void updateList_shouldThrowExceptionIfNotFound() {
        // GIVEN
        UpdateShoppingListRequest updatedRequest = new UpdateShoppingListRequest("Updated List", "Updated description", List.of());
        when(repository.findById("2")).thenReturn(Optional.empty());
        // WHEN & THEN
        assertThrows(NoSuchElementException.class, () -> service.updateList("2", updatedRequest));
        verify(repository, never()).save(any(ShoppingList.class));
    }

    @Test
    public void updateList_shouldUpdateListById() {
        //GIVEN
        Product product1 = new Product("product 1",1);
        ShoppingList shoppingList = new ShoppingList("1","List 1", "List 1 description", List.of(product1));
        when(repository.findById("1")).thenReturn(Optional.of(shoppingList));
        UpdateShoppingListRequest updatedShoppingList = new UpdateShoppingListRequest("Updated List 1", "List 1 description", List.of(product1));
        //WHEN
        service.updateList("1", updatedShoppingList);
        //THEN
        verify(repository, times(1)).save(new ShoppingList(shoppingList.id(), updatedShoppingList.title(), updatedShoppingList.description(), updatedShoppingList.products()));
    }

    @Test
    public void deleteListById_shouldDeleteListById() {
        //GIVEN
        Product product1 = new Product("product 1",1);
        ShoppingList shoppingList = new ShoppingList("1","List 1", "List 1 description", List.of(product1));
        when(repository.findById("1")).thenReturn(Optional.of(shoppingList));
        //WHEN
        service.deleteShoppingList("1");
        //THEN
        verify(repository, times(1)).deleteById(shoppingList.id());
    }

    @Test
    public void deleteListById_shouldThrowExceptionIfNotFound() {
        // GIVEN
        when(repository.findById("2")).thenReturn(Optional.empty());
        // WHEN & THEN
        assertThrows(NoSuchElementException.class, () -> service.deleteShoppingList("2"));
        verify(repository, never()).deleteById(anyString());
    }
}
