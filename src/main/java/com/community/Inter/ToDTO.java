package com.community.Inter;

import com.community.dto.UserDTO;
import com.community.model.User;

public interface ToDTO {
    <U> U getDTO();
}
