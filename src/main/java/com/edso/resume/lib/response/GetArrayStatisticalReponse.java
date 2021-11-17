package com.edso.resume.lib.response;

import com.edso.resume.lib.entities.SourceEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetArrayStatisticalReponse<T> extends BaseResponse {
    private long total;
    private List<T> rows;
    private List<SourceEntity> sourceCVTotal;

    public GetArrayStatisticalReponse() {
        super();
        this.total = 0;
        this.rows = new ArrayList<>();
        this.sourceCVTotal = new ArrayList<>();
    }

    public String info() {
        return "rc = " + code + ", rd = " + message + ", size = " + (rows != null ? rows.size() : 0) + ", total = " + total;
    }

    public void setSuccess(long total, List<T> rows, List<SourceEntity> sourceCVTotal) {
        super.setSuccess();
        this.total = total;
        this.rows = rows;
        this.sourceCVTotal = sourceCVTotal;
    }
}
