package com.edso.resume.lib.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetArrayCalendarReponse<T> extends BaseResponse{
    private List<T> calendars;

    public GetArrayCalendarReponse() {
        super();
        this.calendars = new ArrayList<>();
    }

    public String info() {
        return "rc = " + code + ", rd = " + message + ", calendars = " + (calendars != null ? calendars.size() : 0);
    }

    public void setSuccess(List<T> calendars) {
        super.setSuccess();
        this.calendars = calendars;
    }

}
