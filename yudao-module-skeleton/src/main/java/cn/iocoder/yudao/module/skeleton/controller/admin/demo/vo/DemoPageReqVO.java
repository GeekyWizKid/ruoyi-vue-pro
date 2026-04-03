package cn.iocoder.yudao.module.skeleton.controller.admin.demo.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 示例 Demo 分页查询 Request VO
 *
 * 说明：
 * 1. 分页请求 VO 继承 PageParam，自动获得 pageNo 和 pageSize 字段
 * 2. 类名格式：{业务}PageReqVO，例如 DemoPageReqVO、UserPageReqVO
 * 3. 字段用于搜索条件，一般是模糊匹配（like）或精确匹配（eq）
 * 4. 如果是列表请求（不分页），类名格式：{业务}ListReqVO，不继承 PageParam
 *
 * @author skeleton
 */
@Schema(description = "管理后台 - 示例分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class DemoPageReqVO extends PageParam {

    @Schema(description = "名称，模糊匹配", example = "芋道")
    private String name;

    @Schema(description = "状态，参见 CommonStatusEnum 枚举", example = "0")
    private Integer status;

}
