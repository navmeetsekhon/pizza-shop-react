package com.orderManagement.orderManagementApp.model;

//import jakarta.persistence.Column;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "shopping_cart")
public class UserCart {
    @Id
    private String cartMappingId;
    @Column(name = "user_id")
    private String userId;
}
