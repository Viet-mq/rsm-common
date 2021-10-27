package com.edso.resume.lib.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetResponse<T> extends BaseResponse {
    private T profile;

    public GetResponse() {
        super();
    }

    public String info() {
        return "rc = " + code + ", rd = " + message + ", profile = " + profile;
    }

    public void setSuccess(T profile) {
        super.setSuccess();
        this.profile = profile;
    }
}
