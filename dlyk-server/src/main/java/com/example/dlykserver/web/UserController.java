package com.example.dlykserver.web;

import com.example.dlykserver.model.User;
import com.example.dlykserver.result.R;
import com.example.dlykserver.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/api/login/info")
    public R getUserInfo(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return R.OK(user);
    }

    @GetMapping("/api/login/free")
    public R freeLogin() {
        return R.OK();
    }


    @GetMapping("/api/users")
    public R getUsers(@RequestParam(value = "current", required = false) Integer current) {
        if (current == null) {
            current = 1;
        }
        PageInfo<User> userByPage = userService.getUserByPage(current);
        return R.OK(userByPage);
    }
}
