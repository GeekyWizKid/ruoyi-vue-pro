package cn.iocoder.yudao.module.skeleton.controller.admin.demo.vo;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 示例 Demo 创建/修改 Request VO
 *
 * 说明：
 * 1. VO（View Object）存放在 controller/{admin|app}/{业务}/vo 目录下
 * 2. 创建和修改共用同一个 SaveReqVO，通过 id 是否为空区分新建/更新
 * 3. 类名格式：{业务}SaveReqVO，例如 DemoSaveReqVO、UserSaveReqVO
 * 4. 使用 @Schema 注解生成 Swagger/OpenAPI 文档
 * 5. 使用 JSR-303 注解做参数校验：@NotBlank、@NotNull、@Size、@InEnum 等
 * 6. @InEnum 是项目自定义注解，用于校验枚举值是否合法
 * 7. 字段使用包装类型（Long/Integer），而非基本类型（long/int）
 *
 * @author skeleton
 */
@Schema(description = "管理后台 - 示例创建/修改 Request VO")
@Data
public class DemoSaveReqVO {

    @Schema(description = "编号（新建时不传，修改时必传）", example = "1024")
    private Long id;

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道")
    @NotBlank(message = "名称不能为空")
    @Size(max = 100, message = "名称长度不能超过 100 个字符")
    private String name;

    @Schema(description = "描述", example = "这是一个示例")
    @Size(max = 500, message = "描述长度不能超过 500 个字符")
    private String description;

    @Schema(description = "排序值", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "排序值不能为空")
    private Integer sort;

    @Schema(description = "状态，参见 CommonStatusEnum 枚举", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "状态不能为空")
    @InEnum(CommonStatusEnum.class)
    private Integer status;

    @Schema(description = "备注", example = "随便写点备注")
    private String remark;

}
