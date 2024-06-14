package com.ra.project_md04.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;
import org.springframework.security.core.parameters.P;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shoppingCartId;
    @ManyToOne
    @JoinColumn(nullable = false, name = "product_id",referencedColumnName = "product_id")
    private Products productId;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id",referencedColumnName = "user_id")
    private Users userId;
    private Integer orderQuantity;
}

