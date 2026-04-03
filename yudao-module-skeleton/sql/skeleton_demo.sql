-- ----------------------------
-- skeleton 模块的 SQL 脚本
-- 说明：
-- 1. 表名格式：{模块}_{业务}，例如 skeleton_demo
-- 2. 必须包含审计字段：creator、create_time、updater、update_time、deleted、tenant_id（多租户时）
-- 3. deleted 字段用于逻辑删除（bit 类型，默认 0 表示未删除）
-- 4. tenant_id 字段用于多租户隔离（bigint 类型）
-- 5. 主键使用 bigint 自增
-- 6. 字符串字段推荐使用 varchar 并设置合理长度
-- 7. 状态字段使用 tinyint，关联 CommonStatusEnum（0=开启，1=关闭）
-- ----------------------------

CREATE TABLE IF NOT EXISTS `skeleton_demo` (
    `id`            bigint       NOT NULL AUTO_INCREMENT  COMMENT '编号',
    `name`          varchar(100) NOT NULL DEFAULT ''       COMMENT '名称',
    `description`   varchar(500)          DEFAULT ''       COMMENT '描述',
    `sort`          int          NOT NULL DEFAULT 0        COMMENT '排序值',
    `status`        tinyint      NOT NULL DEFAULT 0        COMMENT '状态（0=开启 1=关闭）',
    `remark`        varchar(500)          DEFAULT ''       COMMENT '备注',
    `creator`       varchar(64)           DEFAULT ''       COMMENT '创建者',
    `create_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`       varchar(64)           DEFAULT ''       COMMENT '更新者',
    `update_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`       bit(1)       NOT NULL DEFAULT b'0'     COMMENT '是否删除',
    `tenant_id`     bigint       NOT NULL DEFAULT 0        COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='示例表';
