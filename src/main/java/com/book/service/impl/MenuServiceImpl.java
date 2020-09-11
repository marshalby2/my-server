package com.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.domain.bean.Menu;
import com.book.mapper.MenuMapper;
import com.book.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author marshal
 * @Date 11/9/20 3:13 PM
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
