package com.ascpm.hofund.jwt.business.service.impl;

import com.ascpm.hofund.jwt.business.service.JwtService;
import com.ascpm.hofund.jwt.data.entity.JwtEntity;
import com.ascpm.hofund.jwt.data.repository.JwtRepository;
import com.ascpm.hofund.jwt.data.response.JwtResponse;
import com.ascpm.hofund.jwt.client.response.KongCredentialCreateResponse;
import com.ascpm.hofund.jwt.client.util.KongUtils;
import com.ascpm.hofund.jwt.comm.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.KeyPair;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Autowired
    private JwtRepository repository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JwtResponse create(String uid) {
        // 기존 정보 find & delete
        this.findAndDelete(uid);
        // 신규 정보 save & create
        return this.saveAndCreate(uid);
    }

    // Kong Credential Delete
    // JwtEntity UseYn 'N' Update
    private void findAndDelete(String uid) {
        this.repository.findOne(this.repository.toPredicate(uid))
                .ifPresent(e -> {
                    // Kong Credential Delete Call
                    KongUtils.deleteCredential(uid, e.getIssuer());
                    // JwtEntity UseYn 'N' Save
                    e.setUseYn(JwtEntity.UseYnType.N);
                    this.save(e);
                });
    }

    // Kong Customer Create(단, 이미 생성되어 있는 경우를 대비하여 응답 값 확인)
    // Kong Credential Create
    // JwtEntity Create
    private JwtResponse saveAndCreate(String uid) {
        // Kong Customer Create Call
        KongUtils.createConsumer(uid);
        // Kong Credential Create Call
        KeyPair keyPair = JwtUtils.getKeyPair();
        KongCredentialCreateResponse credentialResponse =
                KongUtils.createCredential(uid, JwtUtils.getAlgorithm(), JwtUtils.getPem(keyPair.getPublic()));
        // JwtEntity Save
        this.save(JwtEntity.builder()
                .uid(uid)
                .issuer(credentialResponse.getId())
                .useYn(JwtEntity.UseYnType.Y)
                .build());
        // JWT Create
        return JwtResponse.builder()
                .token(JwtUtils.getToken(keyPair.getPrivate(), credentialResponse.getId()))
                .build();
    }

    // Create Or Update
    private JwtEntity save(JwtEntity entity) {
        return this.repository.save(entity);
    }
}
