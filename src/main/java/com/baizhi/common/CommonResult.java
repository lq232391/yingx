package com.baizhi.common;

public class CommonResult {
    private String status;
    private String message;
    private Object data;

    public CommonResult success(String status, String message,Object data){
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(status);
        commonResult.setMessage(message);
        commonResult.setData(data);
        return commonResult;
    }

    public CommonResult success(String message,Object data){
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus("100");
        commonResult.setMessage(message);
        commonResult.setData(data);
        return commonResult;
    }

    public CommonResult success(Object data){
        CommonResult commonResult = new CommonResult();

        commonResult.setStatus("100");
        commonResult.setMessage("请求成功");
        commonResult.setData(data);
        return commonResult;
    }

    public CommonResult failed(String message,Object data){
        CommonResult commonResult = new CommonResult();

        commonResult.setStatus("104");
        commonResult.setMessage(message);
        commonResult.setData(data);

        return commonResult;
    }

    public CommonResult failed(String message){
        CommonResult commonResult = new CommonResult();

        commonResult.setStatus("104");
        commonResult.setMessage(message);
        commonResult.setData(null);
        return commonResult;
    }


    public CommonResult failed(){
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus("104");
        commonResult.setMessage("请求失败");
        commonResult.setData(null);
        return commonResult;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public CommonResult(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public CommonResult() {
        super();
    }
}
