package com.community.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class GiteeIamge {
        private static  final String access_token="3ec80afd039b170274eb820e0c6efa97";
        private static  final String owner="mousetrap/image";
        private static  final String URL="https://gitee.com/api/v5/repos/"+owner+"/contents/avatar/";
        private static  final JsonParser jsonParser=new JsonParser();
        public static String  upload(String path, MultipartFile file) throws IOException {
                String uploadURL = createUploadURL(path);
                Map<String, Object> uploadBody = createUploadBody(file.getBytes());
                String json = HttpUtil.post(uploadURL, uploadBody);
                return getAvatarUrl(json);

        }
        private static String getAvatarUrl(String json){
                JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
                JsonElement content = jsonObject.get("content");
                return content.getAsJsonObject().get("download_url").getAsString();

        }
        public static String createUploadURL(String fileName){
                String suffix = FileUtil.checkSuffis(fileName);
                if(suffix!=null){
                        return URL+UUID.randomUUID().toString()+suffix;
                }
                return null;
        }
        public static Map<String,Object> createUploadBody(byte[] data){
                Map<String,Object> map=new HashMap<>();
                String base64= Base64.encode(data);
                map.put("access_token",access_token);
                map.put("content",base64);
                map.put("message","头像图片");
                return map;
        }

}
