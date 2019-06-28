package com.ascpm.hofund.jwt.data.repository.comm;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;

@NoRepositoryBean
public interface MappingRepository<T> {
    default JPAQuery<T> getQuery(EntityManager entityManager, EntityPathBase<T> qEntity) {
        return new JPAQuery<T>(entityManager).from(qEntity);
    }
}
