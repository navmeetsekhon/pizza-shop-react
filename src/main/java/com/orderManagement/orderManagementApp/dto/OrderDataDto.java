package com.orderManagement.orderManagementApp.dto;

import com.orderManagement.orderManagementApp.dao.MenuDao;
import com.orderManagement.orderManagementApp.model.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDataDto {
    private int tableId;
    private int[] itemId;
    private int[] quantity;
}
