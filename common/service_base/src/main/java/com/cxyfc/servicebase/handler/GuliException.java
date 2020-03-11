package com.cxyfc.servicebase.handler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HASEE
 * @create 2020-03-11 23:47
 * 自定义异常模板类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "异常消息")
    private String msg;

    @Override
    public String toString() {
        return "GuliException{" +
                "message=" + this.getMessage() +
                ", code=" + code +
                '}';
    }
}
