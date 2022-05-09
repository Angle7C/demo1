package com.community.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO {
    private String name;
    private String passWord;
    private String accountId;
    private String avatarUrl;
}
