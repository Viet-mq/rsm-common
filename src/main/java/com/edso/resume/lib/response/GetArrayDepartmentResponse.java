package com.edso.resume.lib.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetArrayDepartmentResponse<T> extends BaseResponse {
    private long total;
    private List<List<T>> rows;

    public GetArrayDepartmentResponse() {
        super();
        this.total = 0;
        this.rows = new ArrayList<>();
    }

    public String info() {
        return "rc = " + code + ", rd = " + message + ", size = " + (rows != null ? rows.size() : 0) + ", total = " + total;
    }

    public void setSuccess(long total, List<List<T>> rows) {
        super.setSuccess();
        this.total = total;
        this.rows = rows;
    }
}
