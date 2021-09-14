package com.edso.resume.lib.entities;

import lombok.Data;

import java.util.List;

@Data
public class HeaderInfo {
    private String username;
    private String fullName;
    private Integer role;
    private List<String> permission;
}
