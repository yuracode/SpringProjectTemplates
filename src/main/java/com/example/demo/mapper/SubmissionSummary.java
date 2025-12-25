package com.example.demo.mapper;

import lombok.Data;

//本来はdtoパッケージなどに置くべきかもしれませんが、今回は簡易的にmapperパッケージに配置しています。
@Data
public class SubmissionSummary {
    private Long id;
    private String username;
    private String roles;
    private Integer score;
}
