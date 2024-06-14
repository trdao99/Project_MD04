package com.ra.project_md04.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRoleKey {
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    Users userid;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    Role roleid;
}
