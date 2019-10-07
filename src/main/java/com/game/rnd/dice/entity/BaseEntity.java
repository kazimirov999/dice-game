package com.game.rnd.dice.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.UUID;

@Data
@MappedSuperclass
@GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
)
@EntityListeners(AuditingEntityListener.class)
abstract class BaseEntity {

    @Id
    @Column(name = "ID", updatable = false)
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();

    @Column(name = "CREATED_ON", updatable = false)
    @CreatedDate
    private Instant createdOn;

    @Column(name = "LAST_MODIFIED_ON")
    @LastModifiedDate
    private Instant lastModifiedOn;
}
