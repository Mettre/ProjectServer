package com.example.myproject.vm;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ModifyPasswordVM {

    @NotBlank(message = "请输入新密码")
    private String newPassword;

    @NotBlank(message = "请输入老密码")
    private String oldPassword;
}
