package com.orderManagement.orderManagementApp.services.impl;


import com.orderManagement.orderManagementApp.dao.MenuDao;
import com.orderManagement.orderManagementApp.dto.ErrorMap;
import com.orderManagement.orderManagementApp.dto.MenuItemRequest;
import com.orderManagement.orderManagementApp.dto.MenuItemResponse;
import com.orderManagement.orderManagementApp.model.MenuItem;
import com.orderManagement.orderManagementApp.services.MenuService;
import com.orderManagement.orderManagementApp.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImp implements MenuService {
    @Autowired
    MenuDao menuDao;

    public ApiResponse<List<MenuItemResponse>> getFullMenu() {
        try {
            List<MenuItemResponse> menuItemResponseList = new ArrayList<>();
            List<MenuItem> menuItemList = menuDao.findAll();
            for (MenuItem m : menuItemList) {
                MenuItemResponse menuItemResponse = new MenuItemResponse(m);
                menuItemResponseList.add(menuItemResponse);
            }
            return new ApiResponse<>(HttpStatus.OK.value(), menuItemResponseList);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), "INTERNAL_SERVER_ERROR");
        }
    }

    public ApiResponse<List<MenuItemResponse>> getItemByCategory(String category) {
        if (category.isEmpty() || category == null)
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value());
        try {
            List<MenuItemResponse> menuItemResponseList = new ArrayList<>();
            List<MenuItem> filteredMenuItems = menuDao.findByItemCategory(category);
            for (MenuItem m : filteredMenuItems) {
                MenuItemResponse menuItemResponse = new MenuItemResponse(m);
                menuItemResponseList.add(menuItemResponse);
            }
            return new ApiResponse<>(HttpStatus.OK.value(), menuItemResponseList);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), "INTERNAL_SERVER_ERROR");
        }
    }

    public ApiResponse<ErrorMap> addMenuItem(MenuItemRequest menuItem) {
        try {
            ErrorMap errorMap = new ErrorMap();
            if (isValidItemDetailsCheck(menuItem, errorMap)) {
                MenuItem item = new MenuItem(menuItem);
                menuDao.save(item);
                return new ApiResponse<>(HttpStatus.OK.value(), errorMap);
            } else {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), errorMap);
            }
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), "INTERNAL_SERVER_ERROR");
        }
    }

    public ApiResponse<ErrorMap> updateItem(long id, MenuItemRequest menuItem) {
        try {
            ErrorMap errorMap = new ErrorMap();
            MenuItem updateItem = menuDao.findByItemId((int) id);
            if (updateItem != null && isValidItemDetailsCheck(menuItem, errorMap)) {
                updateItem.setItemName(menuItem.getItemName());
                updateItem.setItemPrice(menuItem.getItemPrice());
                updateItem.setItemDescription(menuItem.getItemDescription());
                updateItem.setItemCategory(menuItem.getItemCategory());
                menuDao.save(updateItem);
                return new ApiResponse<>(HttpStatus.OK.value(), errorMap);
            } else {
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), errorMap, "item id is invalid", "INVALID_ITEM_ID");
            }
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), "INTERNAL_SERVER_ERROR");
        }
    }
    @Override
    public ApiResponse<ErrorMap> addMultipleItems(List<MenuItemRequest> menuItemRequestListList) {
        try {
            List<MenuItem> menuItemList = new ArrayList<MenuItem>();
            for(MenuItemRequest m : menuItemRequestListList){
                ErrorMap errorMap = new ErrorMap();
                if (isValidItemDetailsCheck(m, errorMap)) {
                    MenuItem item = new MenuItem(m);
                    menuItemList.add(item);
                } else {
                    return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), errorMap);
                }
            }
            menuDao.saveAll(menuItemList);
            return new ApiResponse<>(HttpStatus.OK.value());
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), "INTERNAL_SERVER_ERROR");
        }
    }

    public boolean isValidItemDetailsCheck(MenuItemRequest menuItem, ErrorMap errorMap) {
        boolean res = false;
        Map<String, String> map = new HashMap<>();
        if (menuItem.getItemName() == null || menuItem.getItemName().isEmpty()) {
            map.put("ITEM_NAME", "INVALID");
        }
        if (menuItem.getItemPrice() <= 0) {
            map.put("ITEM_PRICE", "INVALID");
        }
        if (menuItem.getItemDescription().length() > 200 || menuItem.getItemDescription().isEmpty()) {
            map.put("ITEM_DESCRIPTION", "INVALID");
        }
        if (menuItem.getItemCategory().length() > 50 || menuItem.getItemCategory().isEmpty()) {
            map.put("ITEM_CATEGORY", "INVALID");
        }
        if (map.isEmpty()) {
            map.put("DATA", "VALID");
            res = true;
        }
        errorMap.setInvalidDataMap(map);
        return res;
    }
}
