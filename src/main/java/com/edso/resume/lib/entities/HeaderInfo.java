package com.edso.resume.lib.entities;

import com.google.common.base.Strings;
import lombok.Data;

import java.util.List;

@Data
public class HeaderInfo {
    private String username;
    private String fullName;
    private Integer role;
    private List<String> permission;
    private String company;

    public boolean validate() {
        return !Strings.isNullOrEmpty(username) && role != null;
    }

}
