package cn.iocoder.yudao.module.skeleton.controller.admin.demo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 示例 Demo 详情 Response VO
 *
 * 说明：
 * 1. Response VO 用于返回给前端的数据结构
 * 2. 类名格式：{业务}RespVO，例如 DemoRespVO、UserRespVO
 * 3. 包含实体的所有需要展示的字段，以及审计字段（如 createTime）
 * 4. 不需要包含 deleted、updater 等内部使用的字段
 * 5. 如果需要 Excel 导出功能，可以添加 @ExcelProperty 和 @ExcelIgnoreUnannotated 注解
 *
 * @author skeleton
 */
@Schema(description = "管理后台 - 示例详情 Response VO")
@Data
public class DemoRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道")
    private String name;

    @Schema(description = "描述", example = "这是一个示例")
    private String description;

    @Schema(description = "排序值", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer sort;

    @Schema(description = "状态，参见 CommonStatusEnum 枚举", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer status;

    @Schema(description = "备注", example = "随便写点备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
