package com.ra.project_md04.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FormRegister {
    @NotBlank(message = "username is empty")
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String avatar;
    private String phone;
    private String address;
    private List<String> roles;
}