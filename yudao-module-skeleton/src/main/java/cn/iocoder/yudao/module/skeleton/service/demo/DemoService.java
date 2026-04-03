package cn.iocoder.yudao.module.skeleton.service.demo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.skeleton.controller.admin.demo.vo.DemoPageReqVO;
import cn.iocoder.yudao.module.skeleton.controller.admin.demo.vo.DemoSaveReqVO;
import cn.iocoder.yudao.module.skeleton.dal.dataobject.demo.DemoDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 示例 Demo Service 接口
 *
 * 说明：
 * 1. Service 接口存放在 service/{业务} 目录下
 * 2. 类名格式：{业务}Service，例如 DemoService、UserService
 * 3. 方法名规范：
 *    - 创建：create{业务}，返回新记录的 id
 *    - 更新：update{业务}，无返回值
 *    - 删除：delete{业务}（单个）、delete{业务}List（批量）
 *    - 查询单条：get{业务}，返回 DO
 *    - 分页查询：get{业务}Page，返回 PageResult<DO>
 *    - 列表查询：get{业务}List，返回 List<DO>
 *    - 校验存在性：validate{业务}List
 * 4. 参数使用 ReqVO 类型（用于创建/更新）或基本类型（用于查询/删除）
 * 5. 返回值使用 DO 类型，由 Controller 转换为 RespVO
 * 6. 接口方法需要写 Javadoc 注释
 *
 * @author skeleton
 */
public interface DemoService {

    /**
     * 创建示例
     *
     * @param createReqVO 创建信息
     * @return 示例编号
     */
    Long createDemo(@Valid DemoSaveReqVO createReqVO);

    /**
     * 更新示例
     *
     * @param updateReqVO 更新信息
     */
    void updateDemo(@Valid DemoSaveReqVO updateReqVO);

    /**
     * 删除示例
     *
     * @param id 编号
     */
    void deleteDemo(Long id);

    /**
     * 批量删除示例
     *
     * @param ids 编号列表
     */
    void deleteDemoList(List<Long> ids);

    /**
     * 获得示例
     *
     * @param id 编号
     * @return 示例
     */
    DemoDO getDemo(Long id);

    /**
     * 获得示例分页
     *
     * @param pageReqVO 分页查询条件
     * @return 示例分页结果
     */
    PageResult<DemoDO> getDemoPage(DemoPageReqVO pageReqVO);

    /**
     * 获得指定状态的示例列表
     *
     * @param status 状态
     * @return 示例列表
     */
    List<DemoDO> getDemoListByStatus(Integer status);

}
