package com.ra.project_md04.service.impl;

import com.ra.project_md04.model.dto.request.FormLogin;
import com.ra.project_md04.model.dto.request.FormRegister;
import com.ra.project_md04.model.dto.respone.JWTResponse;
import com.ra.project_md04.model.entity.Role;
import com.ra.project_md04.model.entity.Users;
import com.ra.project_md04.model.reponsitory.RoleReponsitory;
import com.ra.project_md04.model.reponsitory.UserReponsitory;
import com.ra.project_md04.sercurity.jwt.JWTProvider;
import com.ra.project_md04.sercurity.principals.CustomUserDetail;
import com.ra.project_md04.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UserService implements IUserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleReponsitory roleRepository;
    @Autowired
    UserReponsitory userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTProvider jwtProvider;

    @Override
    public Boolean registerUser(FormRegister formRegister) {
        Date date = new Date();
        Users user = Users.builder().username(formRegister.getUsername())
                .fullName(formRegister.getFullName())
                .phone(formRegister.getPhone())
                .address(formRegister.getAddress())
                .password(passwordEncoder.encode(formRegister.getPassword()))
                .status(true)
                .avatar("null")
                .createAt(date)
                .updateAt(date)
                .email(formRegister.getEmail())
                .isDelete(false)
                .build();
        List<Role> roles = new ArrayList<>();
        if (formRegister.getRoles() != null && !formRegister.getRoles().isEmpty()) {
            formRegister.getRoles().forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        roles.add(roleRepository.findByRoleName(role).orElseThrow(() -> new NoSuchElementException("Khong ton tai role admin")));
                        break;
                    case "ROLE_USER":
                        roles.add(roleRepository.findByRoleName(role).orElseThrow(() -> new NoSuchElementException("Khong ton tai role user")));
                        break;
                    case "ROLE_MODERATOR":
                        roles.add(roleRepository.findByRoleName(role).orElseThrow(() -> new NoSuchElementException("Khong ton tai role moderator")));
                        break;
                }
            });
        } else {
            roles.add(roleRepository.findByRoleName("ROLE_USER").orElseThrow(() -> new NoSuchElementException("Khong ton tai role user")));
        }
        user.setRoles(roles);
        userRepository.save(user);
        return true;
    }

    @Override
    public JWTResponse loginUser(FormLogin formLogin) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(formLogin.getUsername(), formLogin.getPassword()));
        } catch (AuthenticationException e) {
            System.err.println("Sai username hoac password");
        }


        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        //Tao token tu userDetail
        String token = jwtProvider.createToken(userDetail);

        return JWTResponse.builder()
                .fullName(userDetail.getFullName())
                .address(userDetail.getAddress())
                .email(userDetail.getEmail())
                .phone(userDetail.getPhone())
                .status(userDetail.isStatus())
                .authorities(userDetail.getAuthorities())
                .token(token)
                .build();
    }

    @Override
    public  List<Users> findUserByFullName(String username) {
      return userRepository.findUsersByFullName(username);

    }

    @Override
    public Users changeStatus(Integer id) {
      Users u =  findUserByID(id);
        u.setStatus(!u.isStatus());
        userRepository.save(u);
        return u;
    }

    @Override
    public Users findUserByID(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public Page<Users> getUserPaging(String searchName, Integer page, Integer itemPage, String orderBy, String direction) {
        Pageable pageable = null;

        if(orderBy!=null && !orderBy.isEmpty()){
            // co sap xep
            Sort sort = null;
            switch (direction){
                case "ASC":
                    sort = Sort.by(orderBy).ascending();
                    break;
                case "DESC":
                    sort = Sort.by(orderBy).descending();
                    break;
            }
            pageable = PageRequest.of(page, itemPage,sort);
        }else{
            //khong sap xep
            pageable = PageRequest.of(page, itemPage);
        }

        //xu ly ve tim kiem
        if(searchName!=null && !searchName.isEmpty()){
            //co tim kiem
            return userRepository.findUsersByFullNameAndSorting(searchName,pageable);
        }else{
            //khong tim kiem
            return userRepository.findAll(pageable);
        }
    }


}