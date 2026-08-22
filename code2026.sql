-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS `code2026`;
USE `code2026`;

-- 创建用户表（修改username长度为191）
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username` VARCHAR(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
    `password` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
    `name` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
    `avatar` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
    `role` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
    `account` DECIMAL(10,2) DEFAULT NULL COMMENT '账户余额',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='普通用户';

-- 插入示例数据
INSERT INTO `user` (`username`, `password`, `name`, `avatar`, `role`, `account`) VALUES
     ('zhangsan', 'pass123', '张三', 'https://example.com/avatar/zhangsan.jpg', 'user', 500.50),
     ('lisi', 'pass456', '李四', 'https://example.com/avatar/lisi.jpg', 'user', 200.00),
     ('wangwu', 'pass789', '王五', 'https://example.com/avatar/wangwu.jpg', 'manager', 1500.00),
     ('zhaoliu', 'pass000', '赵六', NULL, 'user', 50.00),
     ('sunqi', 'pass111', '孙七', 'https://example.com/avatar/sunqi.jpg', 'user', 300.00),
     ('zhouba', 'pass222', '周八', NULL, 'manager', 800.00),
     ('wujiu', 'pass333', '吴九', 'https://example.com/avatar/wujiu.jpg', 'admin', 2000.00);

-- 创建管理员表（修改username长度为191）
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
     `id` INT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
     `username` VARCHAR(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
     `password` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
     `name` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
     `avatar` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
     `role` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='管理员信息';

USE `code2026`;
-- 清空并重新插入
TRUNCATE TABLE admin;
INSERT INTO admin (username, password, name, avatar, role)
VALUES ('admin', 'admin123', '管理员', NULL, '管理员');

-- 验证数据
SELECT * FROM admin;