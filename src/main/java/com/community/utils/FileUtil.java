package com.community.utils;

import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileUtil {
    private static List<String> suffix=new ArrayList<>();
    static {
        suffix.add(".png");
        suffix.add(".jpg");
        suffix.add(".jpeg");
    }
    public static String checkSuffis(String name){
        for(String end:suffix){
            if(StringUtils.endsWithIgnoreCase(name,end)){
                return end;
            }
        }
        return null;
    }

}
