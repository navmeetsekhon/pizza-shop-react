package com.orderManagement.orderManagementApp.controllers;

import com.orderManagement.orderManagementApp.dto.ErrorMap;
import com.orderManagement.orderManagementApp.dto.MenuItemRequest;
import com.orderManagement.orderManagementApp.dto.MenuItemResponse;
import com.orderManagement.orderManagementApp.exception.ResourceNotFoundException;
import com.orderManagement.orderManagementApp.model.MenuItem;
import com.orderManagement.orderManagementApp.services.MenuService;
import com.orderManagement.orderManagementApp.services.impl.MenuServiceImp;
import com.orderManagement.orderManagementApp.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    MenuServiceImp menuServiceImp;

    @GetMapping
    public ApiResponse<List<MenuItemResponse>> getMenu() {
        return menuServiceImp.getFullMenu();
    }

    @GetMapping("/category")
    public ApiResponse<List<MenuItemResponse>> getMenuByCategory(@RequestParam String category) {
        return menuServiceImp.getItemByCategory(category);
    }

    @PostMapping("add")
    public ApiResponse<ErrorMap> addItem(@RequestBody MenuItemRequest menuItemRequest) {
        return menuServiceImp.addMenuItem(menuItemRequest);
    }

    @PostMapping("addBulk")
    public ApiResponse<ErrorMap> addItem(@RequestBody List<MenuItemRequest> menuItemRequest) {
        return menuServiceImp.addMultipleItems(menuItemRequest);
    }

    @PutMapping("update")
    public ApiResponse<ErrorMap> updateMenuItem(@RequestParam long id, @RequestBody MenuItemRequest menuItemRequest) {
        return menuServiceImp.updateItem(id, menuItemRequest);
    }

}
