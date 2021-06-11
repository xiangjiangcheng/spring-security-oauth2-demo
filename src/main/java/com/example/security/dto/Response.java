package com.example.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Xiang JiangCheng
 */
@ApiModel(value = "响应实体类")
public class Response<T> {

    @ApiModelProperty(value = "业务状态码, 0 - 表示成功,其他表示失败")
    private int status;

    @ApiModelProperty(value = "业务状态码描述信息")
    private String msg;

    @ApiModelProperty(value = "响应数据")
    private T data;

    public Response() {
    }

    public Response(T data) {
        this.data = data;
    }

    public Response(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Response(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Response<T> success() {
        return new Response<>(0, "success");
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(0, "success", data);
    }

    public static <T> Response<T> failed(int status, String msg) {
        return new Response<>(status, msg);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
