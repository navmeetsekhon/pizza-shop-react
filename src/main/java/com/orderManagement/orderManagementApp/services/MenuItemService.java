package com.orderManagement.orderManagementApp.services;


import com.orderManagement.orderManagementApp.dao.MenuDao;
import com.orderManagement.orderManagementApp.model.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
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
}
