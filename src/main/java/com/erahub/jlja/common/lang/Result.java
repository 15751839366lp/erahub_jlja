package com.erahub.jlja.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private int code;   //200正常，非200异常
    private String msg;
    private Object data;

    /**
     * 返回结果和状态，下同
     * @param data
     * @return
     */
    public static Result succ(Object data){
        Result r = new Result();
        r.setCode(200);
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }

    public static Result succ(String msg, Object data) {
        Result r = new Result();
        r.setCode(200);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public static Result fail(String msg) {
        Result r = new Result();
        r.setCode(400);
        r.setData(null);
        r.setMsg(msg);
        return r;
    }

    public static Result fail(String msg, Object data) {
        Result r = new Result();
        r.setCode(400);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

}
