package com.ascpm.hofund.jwt.business.service;


import com.ascpm.hofund.jwt.data.response.JwtResponse;

public interface JwtService {

    JwtResponse create(String uid);
}
