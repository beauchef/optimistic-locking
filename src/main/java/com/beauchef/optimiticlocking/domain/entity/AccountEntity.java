package com.beauchef.optimiticlocking.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * @author beauchef on 2018-03-11.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "accounts")
@EntityListeners(AuditingEntityListener.class)
public class AccountEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    @Column
    private Integer version;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private int balance;
    @CreatedBy
    protected String createdBy;
    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date creationDate;
    @LastModifiedBy
    protected String lastModifiedBy;
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;
}
