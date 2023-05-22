package com.nhnacademy.residentmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Member.
 */
@Getter
@Setter
@Entity
@Table(name = "Members")
public class Member {
    @Id
    @Column(name = "member_id")
    private String id;

    private String name;

    private String password;

    @OneToOne(mappedBy = "member", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Authority authority;

}
