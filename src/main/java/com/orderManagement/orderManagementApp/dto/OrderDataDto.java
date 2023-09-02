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

    public float calculateTotal(OrderDataDto orderDataDto,MenuDao menuDao){
        float total=0;
        for (int i=0;i<orderDataDto.getItemId().length;i++){
            MenuItem menuItem=menuDao.findByItemId(itemId[i]);
            total= total+((orderDataDto.itemId[i]*orderDataDto.quantity[i])*menuItem.getItemPrice());
        }
        return total;
    }
}
