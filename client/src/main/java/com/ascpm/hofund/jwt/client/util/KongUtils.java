package com.ascpm.hofund.jwt.client.util;

import com.ascpm.hofund.jwt.client.response.KongCredentialCreateResponse;
import com.ascpm.hofund.jwt.client.exception.KongException;
import com.ascpm.hofund.jwt.client.props.KongProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class KongUtils {
    // parameter camelcase to underscore
    private static KongProps kongProps;
    private static WebClient client;

    @Autowired
    public KongUtils(KongProps kongProps) {
        KongUtils.kongProps = kongProps;
        KongUtils.client = WebClient.builder()
                .baseUrl(KongUtils.kongProps.getBaseUrl())
                .build();
    }

    public static void createConsumer(final String uid) {
        client.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("username", uid))
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() && !httpStatus.equals(HttpStatus.CONFLICT),
                        clientResponse -> Mono.error(KongException.builder()
                                .type(KongException.Type.CREATE_CONSUMER_CLIENT_ERROR)
                                .status(clientResponse.statusCode())
                                .build()))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(KongException.builder()
                        .type(KongException.Type.CREATE_CONSUMER_SERVER_ERROR)
                        .status(clientResponse.statusCode())
                        .build()))
                .bodyToMono(Void.class).block();
    }

    public static KongCredentialCreateResponse createCredential(final String uid,
                                                                final String algorithm, final String pem) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("algorithm", algorithm);
        params.add("rsa_public_key", pem);
        return client.post()
                .uri(uriBuilder -> uriBuilder.path(KongUtils.kongProps.getCredential().getCreateUri())
                        .build(uid))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromFormData(params))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(KongException.builder()
                        .type(KongException.Type.CREATE_CONSUMER_CLIENT_ERROR)
                        .status(clientResponse.statusCode())
                        .build()))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(KongException.builder()
                        .type(KongException.Type.CREATE_CONSUMER_SERVER_ERROR)
                        .status(clientResponse.statusCode())
                        .build()))
                .bodyToMono(KongCredentialCreateResponse.class).block();
    }

    public static void deleteCredential(final String uid, final String issuer) {
        client.delete()
                .uri(uriBuilder -> uriBuilder.path(KongUtils.kongProps.getCredential().getDeleteUri())
                        .build(uid, issuer))
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() && !httpStatus.equals(HttpStatus.NOT_FOUND),
                        clientResponse -> Mono.error(KongException.builder()
                                .type(KongException.Type.DELETE_CREDENTIAL_CLIENT_ERROR)
                                .status(clientResponse.statusCode())
                                .build()))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(KongException.builder()
                        .type(KongException.Type.DELETE_CREDENTIAL_SERVER_ERROR)
                        .status(clientResponse.statusCode())
                        .build()))
                .bodyToMono(Void.class).block();
    }
}
