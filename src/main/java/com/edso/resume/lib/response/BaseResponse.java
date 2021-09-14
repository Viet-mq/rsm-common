package com.edso.resume.lib.response;


import com.edso.resume.lib.common.ErrorCodeDefs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    protected int code;
    protected String message;

    public void setSuccess(String msg) {
        this.code = 0;
        this.message = msg;
    }

    public void setSuccess() {
        this.setSuccess("OK");
    }

    public void setResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseResponse OK() {
        BaseResponse r = new BaseResponse();
        r.setSuccess("OK");
        return r;
    }

    public void setFailed(String message) {
        this.code = ErrorCodeDefs.ERROR_CODE_FAILED;
        this.message = message;
    }

}
