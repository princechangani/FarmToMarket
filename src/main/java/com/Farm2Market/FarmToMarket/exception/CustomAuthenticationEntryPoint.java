package com.Farm2Market.FarmToMarket.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        handleException(response, authException.getMessage(), HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
    private void handleException(HttpServletResponse response, String message, int statusCode, String error) throws IOException {
        response.setContentType("application/json");
        response.setStatus(statusCode);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("status", statusCode);
        responseData.put("error", error);
        responseData.put("message", message);
        response.getOutputStream().println(new ObjectMapper().writeValueAsString(responseData));
    }
}
