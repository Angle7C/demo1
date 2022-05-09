package com.community.controller;

import com.community.enums.ErrorEnum;
import com.community.enums.SucessEnum;
import com.community.model.ResultJson;
import com.community.model.User;
import com.community.services.UserService;
import com.community.utils.GiteeIamge;
import com.community.utils.RequestUntils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class FileContorller {
    @Setter
    @Autowired
    private UserService userService;
    @PostMapping("/uploadImage")
    public ResultJson uploadGitee(MultipartFile file, HttpServletRequest request) throws IOException {
        ResultJson json=new ResultJson(SucessEnum.FILE_UPLOAD);
        User user= RequestUntils.getToken(request, userService);
        if(user!=null){
            String fileName= file.getOriginalFilename();
            String avatar=GiteeIamge.upload(fileName,file);
            user.setAvatarUrl(avatar);
            userService.updataOrCreateUser(user);
        }else{
            json.failed(ErrorEnum.CHECK_USER_LOGIN);
        }
        return json;
    }
}
