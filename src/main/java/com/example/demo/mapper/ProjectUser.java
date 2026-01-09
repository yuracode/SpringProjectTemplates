package com.example.demo.mapper;

import lombok.Data;

//本来はmodelパッケージなどに置くべきかもしれませんが、今回は簡易的にmapperパッケージに配置しています。

@Data
public class ProjectUser {
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private String roles;
}
