package com.ra.project_md04.model.reponsitory;

import com.ra.project_md04.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleReponsitory extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(String roleName);
    @Query("select r from Role r")
    List<Role> getAllRoles();
}
