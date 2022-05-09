package com.community.hander;

import java.util.*;
import java.util.stream.Collectors;

public class TagMap  {
    private static final HashMap<String,Long> map=new HashMap<>();
    private static final  List<String> list=new ArrayList<>();
    static {
        addTag("SpringBoot".toLowerCase(Locale.ROOT));
        addTag("Java".toLowerCase(Locale.ROOT));
        addTag("MyBatis".toLowerCase(Locale.ROOT));;
    }
    public static List<String> getTags(){
        return map.keySet().stream().collect(Collectors.toList());
    }
    public static String[] changeString(long tags){
        List<String> ans=new ArrayList<>();

        long count=0L;
        for(long i=0;i< map.size();i++){
            if((tags&(1<<i))!=0){
                ans.add(list.get((int) i));
            }
        }
        return ans.toArray(new String[ans.size()]);
    }
    public static long getTag(String str) {
        System.out.println(str);
        return map.get(str);
    }
    public static void addTag(String str) {
        map.put(str,1L<<map.size());
        list.add(str);
    }
    public static Long getTagSum(String[] tags){
        Long sum=0L;

        for(String tag:tags){
            sum=sum|getTag(tag.toLowerCase(Locale.ROOT));
        }
        return  sum;
    }
}
