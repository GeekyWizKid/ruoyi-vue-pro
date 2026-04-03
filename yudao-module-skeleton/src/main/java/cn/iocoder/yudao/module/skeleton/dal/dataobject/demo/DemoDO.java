package cn.iocoder.yudao.module.skeleton.dal.dataobject.demo;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 示例 Demo DO
 *
 * 说明：
 * 1. DO（Data Object）是数据库表对应的实体类，存放在 dal/dataobject 目录下
 * 2. 类名格式：{业务}DO，例如 DemoDO、UserDO、OrderDO
 * 3. 表名格式：{模块}_{业务}，例如 skeleton_demo、system_user
 * 4. 继承 TenantBaseDO（多租户场景）或 BaseDO（非多租户场景）
 *    - BaseDO 自动提供 createTime、updateTime、creator、updater、deleted 字段
 *    - TenantBaseDO 在 BaseDO 基础上额外提供 tenantId 字段
 * 5. 使用 @TableName 注解映射数据库表名
 * 6. 使用 @KeySequence 注解兼容 Oracle/PostgreSQL 等数据库的主键自增
 * 7. 使用 @TableId 标记主键字段
 * 8. 枚举字段用 Integer 存储，通过 Javadoc 注释关联枚举类，例如 {@link CommonStatusEnum}
 *
 * @author skeleton
 */
@TableName("skeleton_demo")
@KeySequence("skeleton_demo_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
public class DemoDO extends TenantBaseDO {

    /**
     * 编号（主键，自增）
     */
    @TableId
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;

}
