package com.welab.k8s_backend_user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SiteUserLoginDto {

    @NotBlank(message = "사용자 아이디를 입력하세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;
}
