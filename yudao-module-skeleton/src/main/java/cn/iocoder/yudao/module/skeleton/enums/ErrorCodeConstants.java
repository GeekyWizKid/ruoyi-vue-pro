package cn.iocoder.yudao.module.skeleton.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Skeleton 错误码枚举类
 *
 * 说明：
 * 1. 每个模块有独立的错误码段，避免冲突。错误码格式：1-{模块编号}-{业务编号}-{序号}
 *    例如：skeleton 模块使用 1-100-000-000 段
 * 2. 每个业务使用独立的编号段：
 *    - demo 业务：1-100-000-000 ~ 1-100-000-999
 * 3. ErrorCode 包含 code（Integer）和 msg（String）两个字段
 * 4. msg 支持 {} 占位符，用于运行时填充动态参数
 * 5. 使用方式：throw exception(DEMO_NOT_EXISTS) 或 throw exception(DEMO_NAME_DUPLICATE, name)
 *
 * skeleton 示例，使用 1-100-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== Demo 模块 1-100-000-000 ==========
    ErrorCode DEMO_NOT_EXISTS = new ErrorCode(1_100_000_000, "示例不存在");
    ErrorCode DEMO_NAME_DUPLICATE = new ErrorCode(1_100_000_001, "已经存在名为【{}】的示例");

}
