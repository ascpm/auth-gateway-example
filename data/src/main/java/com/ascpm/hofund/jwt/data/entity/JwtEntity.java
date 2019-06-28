package com.ascpm.hofund.jwt.data.entity;

import com.ascpm.hofund.jwt.data.entity.comm.MappingType;
import com.ascpm.hofund.jwt.data.entity.comm.MappingTypeConverter;
import com.ascpm.hofund.jwt.data.entity.comm.MappingEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "`jwt`")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class JwtEntity extends MappingEntity implements Serializable {

    private static final long serialVersionUID = -2744151920354115727L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false)
    private long id;

    @Column(name = "`uid`", length = 40, nullable = false)
    private String uid;

    @Column(name = "`issuer`", length = 100, nullable = false)
    private String issuer;

    @Column(name = "`use_yn`", length = 1, nullable = false)
    @Convert(converter = JwtEntity.UseYnTypeConverter.class)
    private UseYnType useYn;

    @Builder
    public JwtEntity(Timestamp createdTime,
                     Timestamp modifiedTime,
                     int id, String uid, String issuer, UseYnType useYn) {
        super(createdTime, modifiedTime);
        this.id = id;
        this.uid = uid;
        this.issuer = issuer;
        this.useYn = useYn;
    }

    @Getter
    @AllArgsConstructor
    public enum UseYnType implements MappingType<String> {
        Y("Y", "사용"),
        N("N", "미사용");

        private String code;
        private String desc;
    }

    public static class UseYnTypeConverter implements MappingTypeConverter<UseYnType, String> {
        @Override
        public JwtEntity.UseYnType[] getTypes() {
            return JwtEntity.UseYnType.values();
        }
    }
}
