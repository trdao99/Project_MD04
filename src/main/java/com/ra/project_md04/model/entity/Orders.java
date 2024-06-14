package com.ra.project_md04.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;


import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    private String serialNumber;
    private Double totalPrice;
    private String status;
    private String note;
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private Users userId;
}
