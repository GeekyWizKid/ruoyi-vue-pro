package cn.iocoder.yudao.module.skeleton.controller.admin.demo;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.skeleton.controller.admin.demo.vo.DemoPageReqVO;
import cn.iocoder.yudao.module.skeleton.controller.admin.demo.vo.DemoRespVO;
import cn.iocoder.yudao.module.skeleton.controller.admin.demo.vo.DemoSaveReqVO;
import cn.iocoder.yudao.module.skeleton.controller.admin.demo.vo.DemoSimpleRespVO;
import cn.iocoder.yudao.module.skeleton.dal.dataobject.demo.DemoDO;
import cn.iocoder.yudao.module.skeleton.service.demo.DemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 示例 Demo Controller
 *
 * 说明：
 * 1. Controller 存放在 controller/{admin|app}/{业务} 目录下
 *    - admin 目录：管理后台的接口（需要登录 + 权限校验）
 *    - app 目录：用户 App/小程序端的接口（需要登录，但权限模型不同）
 * 2. 注解说明：
 *    - @Tag：Swagger 分组标签，格式："管理后台 - {业务}"
 *    - @RestController：标记为 REST 控制器
 *    - @RequestMapping：路由前缀，格式："/{模块}/{业务}"
 *    - @Validated：启用参数校验
 * 3. 接口规范：
 *    - 创建：POST /{业务}/create，返回 CommonResult<Long>（新记录 id）
 *    - 更新：PUT /{业务}/update，返回 CommonResult<Boolean>
 *    - 删除：DELETE /{业务}/delete，返回 CommonResult<Boolean>
 *    - 批量删除：DELETE /{业务}/delete-list，返回 CommonResult<Boolean>
 *    - 查询单条：GET /{业务}/get，返回 CommonResult<RespVO>
 *    - 分页查询：GET /{业务}/page，返回 CommonResult<PageResult<RespVO>>
 *    - 精简列表：GET /{业务}/simple-list，返回 CommonResult<List<SimpleRespVO>>
 * 4. 权限校验使用 @PreAuthorize("@ss.hasPermission('{模块}:{业务}:{操作}')")
 * 5. 所有返回值使用 CommonResult<T> 包装
 * 6. Service 注入使用 @Resource 注解
 * 7. DO → VO 转换使用 BeanUtils.toBean() 方法
 *
 * @author skeleton
 */
@Tag(name = "管理后台 - 示例")
@RestController
@RequestMapping("/skeleton/demo")
@Validated
public class DemoController {

    @Resource
    private DemoService demoService;

    @PostMapping("/create")
    @Operation(summary = "创建示例")
    @PreAuthorize("@ss.hasPermission('skeleton:demo:create')")
    public CommonResult<Long> createDemo(@Valid @RequestBody DemoSaveReqVO createReqVO) {
        Long demoId = demoService.createDemo(createReqVO);
        return success(demoId);
    }

    @PutMapping("/update")
    @Operation(summary = "更新示例")
    @PreAuthorize("@ss.hasPermission('skeleton:demo:update')")
    public CommonResult<Boolean> updateDemo(@Valid @RequestBody DemoSaveReqVO updateReqVO) {
        demoService.updateDemo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除示例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('skeleton:demo:delete')")
    public CommonResult<Boolean> deleteDemo(@RequestParam("id") Long id) {
        demoService.deleteDemo(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除示例")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('skeleton:demo:delete')")
    public CommonResult<Boolean> deleteDemoList(@RequestParam("ids") List<Long> ids) {
        demoService.deleteDemoList(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得示例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('skeleton:demo:query')")
    public CommonResult<DemoRespVO> getDemo(@RequestParam("id") Long id) {
        DemoDO demo = demoService.getDemo(id);
        return success(BeanUtils.toBean(demo, DemoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得示例分页")
    @PreAuthorize("@ss.hasPermission('skeleton:demo:query')")
    public CommonResult<PageResult<DemoRespVO>> getDemoPage(@Validated DemoPageReqVO pageReqVO) {
        PageResult<DemoDO> pageResult = demoService.getDemoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DemoRespVO.class));
    }

    @GetMapping(value = {"/list-all-simple", "/simple-list"})
    @Operation(summary = "获取示例精简列表", description = "只包含被开启的数据，主要用于前端的下拉选项")
    public CommonResult<List<DemoSimpleRespVO>> getSimpleDemoList() {
        List<DemoDO> list = demoService.getDemoListByStatus(CommonStatusEnum.ENABLE.getStatus());
        return success(BeanUtils.toBean(list, DemoSimpleRespVO.class));
    }

}
