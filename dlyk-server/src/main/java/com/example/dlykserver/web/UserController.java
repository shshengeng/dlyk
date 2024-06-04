package com.example.dlykserver.web;

import com.example.dlykserver.model.User;
import com.example.dlykserver.query.UserQuery;
import com.example.dlykserver.result.R;
import com.example.dlykserver.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/user/{id}")
    public R getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return R.OK(user);
    }

    @PostMapping("/api/user")
    public R addUser(UserQuery userQuery, @RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token =  token.substring(7);
        }
        userQuery.setToken(token);
        int save = userService.saveUser(userQuery);
        return save == 1 ? R.OK() : R.FAIL();
    }


    @PutMapping("/api/user")
    public R updateUser(UserQuery userQuery, @RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token =  token.substring(7);
        }
        userQuery.setToken(token);
        int update = userService.updateUser(userQuery);
        return update == 1 ? R.OK() : R.FAIL();
    }


    @DeleteMapping("/api/user/{id}")
    public R deleteUserById(@PathVariable Long id) {
       int del = userService.deleteUser(id);
       return del == 1 ? R.OK() : R.FAIL();
    }
}
