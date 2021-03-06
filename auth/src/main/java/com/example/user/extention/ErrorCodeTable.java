package com.example.user.extention;

/**
 * Created by IBM on 2016/8/1.
 * 错误码表
 */
public enum  ErrorCodeTable {

    UserNameAlreadyExists("0001","UserNameAlreadyExists","请求注册的用户名已经被使用"),
    AuthFailure("0002","AuthFailure","用户名或密码错误"),
    UserNotExists("0003","UserNotExists","用户不存在"),
    UserNotLogin("0004","UserNotLogin","用户未登录"),
    ServerException("0005","ServerException","内部服务异常")
    ;

    private String code;
    private String msg;
    private String msgCN;

    private ErrorCodeTable(String code, String msg,String msgCN) {
        this.code = code;
        this.msg = msg;
        this.msgCN=msgCN;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getMsgCN(){
        return msgCN;
    }

    @Override
    public String toString() {
        return "ErrorCodeTable{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", msgCN='"+msgCN+'\'' +
                '}';
    }
}
