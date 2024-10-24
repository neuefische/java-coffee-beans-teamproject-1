package com.example.backend.service;

import com.example.backend.dto.UpdateShoppingListRequest;
import com.example.backend.model.ShoppingList;
import com.example.backend.repository.ShoppingListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingListService {
    private static final String LIST_NOT_FOUND_MESSAGE = "List not found";

    private final IdService idservice;
    private final ShoppingListRepository shoppingListRepository;

    public List<ShoppingList> getAllLists() {
        return shoppingListRepository.findAll();
    }

    public ShoppingList getListById(String id) {

        return shoppingListRepository.findById(id).orElseThrow(() -> new NoSuchElementException(LIST_NOT_FOUND_MESSAGE));
    }

    public ShoppingList createShoppingList(ShoppingList shoppingList) {
        String shoppingListId = idservice.randomId();

        ShoppingList newShoppingList = new ShoppingList(shoppingListId, shoppingList.title(), shoppingList.description(), shoppingList.products());
        return shoppingListRepository.save(newShoppingList);
    }

    public ShoppingList updateList (String id, UpdateShoppingListRequest request) {
        ShoppingList listToUpdate = shoppingListRepository.findById(id).orElse(null);
        if (listToUpdate != null) {
            ShoppingList newList = new ShoppingList(listToUpdate.id(), request.title(), request.description(), request.products());
            return shoppingListRepository.save(newList);
        }
        else throw new NoSuchElementException (LIST_NOT_FOUND_MESSAGE);
    }

    public void deleteShoppingList(String id) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(id);

        if(shoppingList.isPresent()) {
            shoppingListRepository.deleteById(id);
        } else {
            throw new NoSuchElementException (LIST_NOT_FOUND_MESSAGE);
        }
    }
}
