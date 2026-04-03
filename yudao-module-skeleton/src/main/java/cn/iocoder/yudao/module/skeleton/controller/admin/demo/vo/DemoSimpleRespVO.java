package cn.iocoder.yudao.module.skeleton.controller.admin.demo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 示例 Demo 精简 Response VO
 *
 * 说明：
 * 1. SimpleRespVO 用于下拉框、选择器等场景，只包含最少的字段（通常是 id + name）
 * 2. 类名格式：{业务}SimpleRespVO，例如 DemoSimpleRespVO、DeptSimpleRespVO
 * 3. 接口通常不需要权限校验（因为只是获取选项列表）
 *
 * @author skeleton
 */
@Schema(description = "管理后台 - 示例精简 Response VO")
@Data
public class DemoSimpleRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道")
    private String name;

}
