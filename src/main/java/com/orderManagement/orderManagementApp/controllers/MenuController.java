package com.orderManagement.orderManagementApp.controllers;

import com.orderManagement.orderManagementApp.model.MenuItem;
import com.orderManagement.orderManagementApp.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController
{
    @Autowired
    MenuItemService menuItemService;
    @GetMapping("/getMenu")
    public List<MenuItem> getMenu(){
        return menuItemService.getFullMenu();
    }
    @GetMapping("category/{category}")
    public List<MenuItem> getMenuByCategory(@PathVariable String category){
        return menuItemService.getItemByCategory(category);
    }
    @PostMapping("add")
    public String addItem(@RequestBody MenuItem menuItem){
        return menuItemService.addMenuItem(menuItem);
    }

}
