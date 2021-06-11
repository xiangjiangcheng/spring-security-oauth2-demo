package com.example.security.config.exception;

/**
 * @author Xiang JiangCheng
 */
public enum ErrorEnum {
    // 数据操作错误定义
    INVALID_PARAM(2000, "参数不合法"),
    ILLEGAL_OPERATION(2002, "非法操作"),
    ;

    /**
     * 错误码
     */
    private int errorCode;

    /**
     * 错误描述
     */
    private String errorMsg;

    ErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
