package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.service.AdminService;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Resource
    private AdminService adminService;

    @Resource
    private UserService userService;


    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest) {

        // 1. 参数校验（保持你原有的逻辑）
        if (loginRequest == null) {
            return Result.error("请求参数为空");
        }
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if (username == null || username.trim().isEmpty()) {
            return Result.error("用户名不能为空");
        }
        if (password == null || password.trim().isEmpty()) {
            return Result.error("密码不能为空");
        }

        // 2. 封装成 Account 对象，并设置角色
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setRole(loginRequest.getRole()); // 关键：从请求中获取角色

        if (account.getRole() == null) {
            throw new RuntimeException("角色值为 null！");
        }

        try {
            Account loginAccount = null;
            // 3. 根据角色明确分流
            if ("管理员".equals(account.getRole())) {
                loginAccount = adminService.login(account);
            } else if ("普通用户".equals(account.getRole())) {
                loginAccount = userService.login(account);
            } else {
                return Result.error("未知的用户角色");
            }

            if (loginAccount != null) {
                return Result.success(loginAccount);
            } else {
                return Result.error("登录失败，用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            String roleName = "管理员".equals(account.getRole()) ? "管理员" : "普通用户";
            return Result.error(roleName + "登录失败：" + e.getMessage());
        }
    }

    public static class LoginRequest {
        private String username;
        private String password;
        private String role;

        // 显式添加无参构造函数
        public LoginRequest() {
        }

        // 保留所有手写的 getter 和 setter
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
