package com.ra.project_md04.model.entity;

import jakarta.persistence.*;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false,name = "category_id")
    private Long categoryId;
    private String categoryName;
    private String description;
    private Boolean status;
}
