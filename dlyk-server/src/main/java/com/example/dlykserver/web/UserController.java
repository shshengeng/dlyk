package com.example.dlykserver.web;

import com.example.dlykserver.model.User;
import com.example.dlykserver.result.R;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/login/info")
    public R getUserInfo(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return R.OK(user);
    }

    @GetMapping("/api/login/free")
    public R freeLogin() {
        return R.OK();
    }
}
