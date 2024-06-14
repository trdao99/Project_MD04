package com.ra.project_md04.sercurity.principals;

import com.ra.project_md04.model.entity.Role;
import com.ra.project_md04.model.entity.Users;
import com.ra.project_md04.model.reponsitory.UserReponsitory;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserReponsitory userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findUserByUsername(username).orElseThrow(() -> new NoSuchElementException("Khong ton tai username"));
        //chuyen tu user ve CustomUserDetail de luu vao SecurityContextHolder
        CustomUserDetail userDetail = CustomUserDetail.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .address(user.getAddress())
                .email(user.getEmail())
                .phone(user.getPhone())
                .status(user.isStatus())
                .avatar(user.getAvatar())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .isDelete(user.isDelete())
                .authorities(functionConvertRoleToGrandAuthorities(user.getRoles()))
                .build();
        return userDetail;
    }
    private List<? extends GrantedAuthority> functionConvertRoleToGrandAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).toList();
    }
}
