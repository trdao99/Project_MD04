package com.ra.project_md04.service.impl;

import com.ra.project_md04.model.entity.Role;
import com.ra.project_md04.model.reponsitory.RoleReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService implements com.ra.project_md04.service.RoleService {
    @Autowired
    RoleReponsitory roleReponsitory;
    @Override
    public List<Role> getAllRoles() {
        return roleReponsitory.getAllRoles();
    }
}
