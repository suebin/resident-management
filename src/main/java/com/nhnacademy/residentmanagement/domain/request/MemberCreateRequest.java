package com.nhnacademy.residentmanagement.domain.request;

import lombok.Data;

@Data
public class MemberCreateRequest {
    private String id;
    private String name;
    private String password;
    private String authority;
}
