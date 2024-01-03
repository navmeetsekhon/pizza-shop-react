package com.orderManagement.orderManagementApp.services;


import com.orderManagement.orderManagementApp.dao.MenuDao;
import com.orderManagement.orderManagementApp.exception.ResourceNotFoundException;
import com.orderManagement.orderManagementApp.model.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {
    @Autowired
    MenuDao menuDao;
    public List<MenuItem> getFullMenu() {
        return menuDao.findAll();
    }

    public List<MenuItem> getItemByCategory(String category) {
        return menuDao.findByItemCategory(category);
    }

    public String addMenuItem(MenuItem menuItem) {
        menuDao.save(menuItem);
        return "success";
    }

    public ResponseEntity<MenuItem> updateItem(long id, MenuItem menuItem) {
            MenuItem updateItem=menuDao.findByItemId((int)id);
            if (updateItem!=null){
                updateItem.setItemName(menuItem.getItemName());
                updateItem.setItemPrice(menuItem.getItemPrice());
                updateItem.setItemDescription(menuItem.getItemDescription());
                updateItem.setItemCategory(menuItem.getItemCategory());
                menuDao.save(updateItem);
                System.out.println("item updated");
                return ResponseEntity.ok(updateItem);
            }
            else {
                return ResponseEntity.notFound().build();
            }

    }
}
