package com.orderManagement.orderManagementApp.controllers;

import com.orderManagement.orderManagementApp.exception.ResourceNotFoundException;
import com.orderManagement.orderManagementApp.model.MenuItem;
import com.orderManagement.orderManagementApp.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController
{
    @Autowired
    MenuItemService menuItemService;
    @GetMapping
    public List<MenuItem> getMenu(){
        return menuItemService.getFullMenu();
    }
    @GetMapping("category")
    public List<MenuItem> getMenuByCategory(@RequestParam String category){
        try {
            return menuItemService.getItemByCategory(category);
        }
        catch (ResourceNotFoundException e){
            System.out.println("Category not present in menu");
            return Collections.emptyList();
        }
            }
    @PostMapping("add")
    public String addItem(@RequestBody MenuItem menuItem){
        return menuItemService.addMenuItem(menuItem);
    }
    @PutMapping("update")
    public ResponseEntity<MenuItem> updateMenuItem(@RequestParam long id,@RequestBody MenuItem menuItem){
        return menuItemService.updateItem(id,menuItem);
    }

}
