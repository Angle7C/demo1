package com.community.dto;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class SearchDTO {
    private String sort;
    private String[] tag;
    private String search;
    private Page page;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Page{

        private Integer pageSize;
        private Integer pageIndex;
        private Integer totalPage;

    }
    public Integer getPageSize(){
        return page.getPageSize();
    }
    public Integer getPageIndex(){
        return page.getPageIndex();
    }
}
