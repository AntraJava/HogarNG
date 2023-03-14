package com.hogarcontrols.hogarcloud.admin.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class HogarAccessDenyHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException {
        log.error("Responding with unauthorized error. Message - {}", exc.getMessage());
        response.sendError(HttpServletResponse.SC_FORBIDDEN, exc.getMessage());
    }
}
