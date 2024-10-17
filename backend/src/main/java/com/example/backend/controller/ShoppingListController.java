package com.example.backend.controller;

import com.example.backend.dto.CreateShoppingListRequest;
import com.example.backend.model.ShoppingList;
import com.example.backend.service.ShoppingListService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/lists")
public class ShoppingListController {
    private final ShoppingListService shoppingListService;

    @GetMapping
    public List<ShoppingList> getAllLists(){
        return shoppingListService.getAllLists();
    }

    @GetMapping("/{id}")
    public ShoppingList getListById(@PathVariable String id){
        ShoppingList list = shoppingListService.getListById(id);
        if (list == null) {
            throw new NoSuchElementException("List not found");
        }
        return list;
    }

    @PostMapping
    public ShoppingList save(@RequestBody CreateShoppingListRequest request){
        ShoppingList newShoppingList = shoppingListService.createShoppingList(request.toModel());
        return newShoppingList;
    }

    @PutMapping("/{id}")
    public ShoppingList updateShoppingList (@PathVariable String id, @RequestBody ShoppingList list){
        return shoppingListService.updateList(id, list);
    }

    @DeleteMapping("/{id}")
    public void deleteShoppingList(@PathVariable String id){
        shoppingListService.deleteShoppingList(id);
    }

}
