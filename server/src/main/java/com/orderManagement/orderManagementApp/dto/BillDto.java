package com.orderManagement.orderManagementApp.dto;

// import lombok.AllArgsConstructor;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import com.orderManagement.orderManagementApp.model.MenuItem;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillDto {
    private String userId;
    private String orderId;
    private Timestamp orderDate;
    private List<OrderItemDto> orderItemsList;
    private Double subtotal;
}

