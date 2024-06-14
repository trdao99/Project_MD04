package com.ra.project_md04.sercurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(403);
        response.setHeader(HttpStatus.FORBIDDEN.toString(),"Forbidden");
        Map<String,String> map = new HashMap<>();
        map.put("error","For bidden EntryPoint");
        new ObjectMapper().writeValue(response.getOutputStream(),map);
    }
}
