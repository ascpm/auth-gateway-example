package com.ascpm.hofund.jwt.data.entity.comm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class MappingEntity {
    @Column(name = "`created_time`", nullable = false)
    @CreationTimestamp
    private Timestamp createdTime;

    @Column(name = "`modified_time`")
    @UpdateTimestamp
    private Timestamp modifiedTime;

    @PrePersist
    public void prePersist() {
        this.createdTime = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedTime = Timestamp.valueOf(LocalDateTime.now());
    }
}
