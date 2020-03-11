package com.cxyfc.servicebase.handler;

import com.cxyfc.utils.ExceptionUtil;
import com.cxyfc.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author HASEE
 * @create 2020-03-11 23:42
 * 统一异常处理器
 *
 * log.error(e.getMessage()); 将日志输出到文件
 * log.error(ExceptionUtil.getMessage(e));//将堆栈输出到文件
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();

        log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return R.error();
    }

    //特殊异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return R.error().message("特殊异常处理");
    }


    //使用自定义异常类 处理异常（需要手动抛出异常）
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){
        e.printStackTrace();
        log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return R.error().message(e.getMessage()).code(e.getCode());
    }
}
