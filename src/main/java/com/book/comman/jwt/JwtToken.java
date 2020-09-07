package com.book.comman.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description TODO
 * @Author marshal
 * @Date 7/9/20 4:10 PM
 */
@Data
@AllArgsConstructor
public class JwtToken {

    private String tokenHead;
    private String tokenContent;

}
