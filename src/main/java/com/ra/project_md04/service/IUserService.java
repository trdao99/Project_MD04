package com.ra.project_md04.service;

import com.ra.project_md04.model.dto.request.FormLogin;
import com.ra.project_md04.model.dto.request.FormRegister;
import com.ra.project_md04.model.dto.respone.JWTResponse;
import com.ra.project_md04.model.entity.Users;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    Boolean registerUser(FormRegister formRegister);
    JWTResponse loginUser(FormLogin formLogin);
    List<Users> findUserByFullName(String username);
    Users changeStatus(Integer id);
    Users findUserByID(int id);
    Page<Users> getUserPaging(String searchName, Integer page, Integer itemPage, String orderBy, String direction);

}
