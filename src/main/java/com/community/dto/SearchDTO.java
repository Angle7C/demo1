package com.community.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchDTO {
    private String sort;
    private String[] tag;
    private String search;
    private Integer pageSize;
    private Integer pageIndex;

}
