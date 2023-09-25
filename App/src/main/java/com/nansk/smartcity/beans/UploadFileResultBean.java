package com.nansk.smartcity.beans;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/08 21:28
 * @description 上传文件返回对象
 */

public class UploadFileResultBean {

    /**
     * code : 200
     * fileName : test.txt
     * url : /profile/upload/file/test.txt
     * msg : 操作成功
     */

    public int code;
    public String fileName;
    public String url;
    public String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
