package com.community.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil {
    private static Gson gson=new Gson();
    private static JsonParser jsonParser=new JsonParser();
    public static String getAttribute(String s,String... path){
        JsonElement parse = jsonParser.parse(s);
        JsonObject jsonObject = null;
        System.out.println(parse.isJsonObject());
        if(path.length==1){
            jsonObject=parse.getAsJsonObject();
            return jsonObject.get(path[0]).toString().replace("\"","");
        }else{
            jsonObject=parse.getAsJsonObject();
            for(String item:path){
                JsonElement jsonElement = jsonObject.get(item);
                jsonObject = jsonElement.getAsJsonObject();
            }
            return jsonObject.getAsString().replace("\"","");
        }
    }
}
