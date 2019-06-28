package com.ascpm.hofund.jwt.web.controller;

import com.ascpm.hofund.jwt.business.service.JwtService;
import com.ascpm.hofund.jwt.comm.util.LogUtils;
import com.ascpm.hofund.jwt.data.response.JwtResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api")
@Slf4j
public class JwtController {

    private static final String LOG_INFO = "api";

    @Autowired
    private JwtService service;

    @PostMapping(path = "/create/{uid}")
    public ResponseEntity<JwtResponse> create(HttpServletRequest httpServletRequest,
                                              @PathVariable String uid) {
        log.info(LogUtils.get(httpServletRequest, LOG_INFO, "JWT Token 생성", null));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(uid));
    }
}
