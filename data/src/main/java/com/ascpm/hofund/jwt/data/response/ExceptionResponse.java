package com.ascpm.hofund.jwt.data.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = -5867357267711586453L;

    private long timestamp;
    private int status;
    private String error;
    private String code;
    private String message;
    private String path;
}
