package com.example.springbootexample.controller;

import com.example.springbootexample.dto.Result;
import com.example.springbootexample.exception.BizErrorCodeEnum;
import com.example.springbootexample.exception.BizException;
import com.example.springbootexample.service.ResponseResultBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
/**
 * user :  Dchunzhi
 * time :  2022/11/15 18:08
 * desc : Spring Boot 无侵入式 实现API接口统一JSON格式返回
 *      https://blog.csdn.net/qq_34347620/article/details/102239179
 */

@RestController
@RequestMapping("/test")
@ResponseResultBody
public class TestController {


    private static final HashMap<String, Object> INFO;
    static {
        INFO = new HashMap<>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    @GetMapping("/hello")
    public Map<String,Object> hello(){
        return  INFO;
    }

    @GetMapping("/result")
    public Result<Map<String, Object>> helloResult() {
        return Result.success(INFO);
    }

    @GetMapping("/helloError")
    public HashMap<String, String> helloError() throws Exception {
        throw new Exception("helloError");
    }

    @GetMapping("helloMyError")
    public HashMap<String, Object> helloMyError() throws Exception {
        throw new BizException(BizErrorCodeEnum.PAY_CHANNEL_PARAM_ERROR);
    }

}

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "自己定义的异常")
class MyException extends Exception {

}
