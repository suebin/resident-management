package com.nhnacademy.residentmanagement.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 * Member 권한.
 */
@Getter
@Setter
@Entity
@Table(name = "Authority")
public class Authority {
    @Id
    private String memberId;

    private String authority;

    @MapsId
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
