package com.book.web;

import com.baomidou.mybatisplus.extension.api.R;
import com.book.comman.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author marshal
 * @Date 5/9/20 10:24 AM
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    @ApiOperation(value = "hello")
    public Result hello() {
        return Result.success("hello");
    }

}
