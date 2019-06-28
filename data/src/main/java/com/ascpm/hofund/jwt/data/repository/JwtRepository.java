package com.ascpm.hofund.jwt.data.repository;

import com.ascpm.hofund.jwt.data.repository.comm.MappingRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.ascpm.hofund.jwt.data.entity.JwtEntity;
import com.ascpm.hofund.jwt.data.entity.QJwtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface JwtRepository extends JpaRepository<JwtEntity, Long>, QuerydslPredicateExecutor<JwtEntity>,
        MappingRepository<JwtEntity> {
    default Predicate toPredicate(String uid) {
        QJwtEntity qEntity = QJwtEntity.jwtEntity;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qEntity.uid.eq(uid)).and(qEntity.useYn.eq(JwtEntity.UseYnType.Y));
        return builder;
    }
}
