package com.orderManagement.orderManagementApp.services;

import com.orderManagement.orderManagementApp.dto.ErrorMap;
import com.orderManagement.orderManagementApp.dto.MenuItemRequest;
import com.orderManagement.orderManagementApp.dto.MenuItemResponse;
import com.orderManagement.orderManagementApp.model.MenuItem;
import com.orderManagement.orderManagementApp.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MenuService {
    ApiResponse<List<MenuItemResponse>> getFullMenu();
    ApiResponse<List<MenuItemResponse>> getItemByCategory(String category);
    ApiResponse<ErrorMap> addMenuItem(MenuItemRequest menuItemRequest);
    ApiResponse<ErrorMap> updateItem(long id, MenuItemRequest menuItemRequest);

    ApiResponse<ErrorMap> addMultipleItems(List<MenuItemRequest> menuItemRequestListList);
}
