package com.edso.resume.lib.utils;

import com.edso.resume.lib.common.AppUtils;
import com.edso.resume.lib.common.HeaderDefs;
import com.edso.resume.lib.entities.HeaderInfo;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Map;

public class ParseHeaderUtil {
    public static HeaderInfo build(Map<String, String> headers) {
        final HeaderInfo info = new HeaderInfo();
        headers.forEach((key, value) -> {
            if (!Strings.isNullOrEmpty(key)) {
                if (key.equalsIgnoreCase(HeaderDefs.USER_NAME_IN_HEADER)) {
                    info.setUsername(value);
                }
                if (key.equalsIgnoreCase(HeaderDefs.USER_ROLE)) {
                    info.setRole(AppUtils.parseInt(value));
                }
                if (key.equalsIgnoreCase(HeaderDefs.USER_PERMISSION)) {
                    //info.setRole(AppUtils.parseInt(value));
                }
                if (key.equalsIgnoreCase(HeaderDefs.USER_ORGANIZATION)) {
                    info.setOrganizations(AppUtils.parseList(value));
                }
                if (key.equalsIgnoreCase(HeaderDefs.USER_MY_ORGANIZATION)) {
                    info.setMyOrganizations(AppUtils.parseList(value));
                }
            }
        });
        // fix
        info.setPermission(new ArrayList<>());
        return info;
    }

}
