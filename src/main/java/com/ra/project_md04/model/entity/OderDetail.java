package com.ra.project_md04.model.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class OderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "order_id")
    private Orders orderId;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    private Products productId;
    private String name;
    private Double unitPrice;
    private Integer orderQuantity;
}
