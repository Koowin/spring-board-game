package com.koowin.chesswithreview.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRegisterDto {
    private String name;
    private String password;

    @Builder
    public MemberRegisterDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
