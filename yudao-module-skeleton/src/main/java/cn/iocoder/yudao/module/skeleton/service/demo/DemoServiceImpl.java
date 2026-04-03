package cn.iocoder.yudao.module.skeleton.service.demo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.skeleton.controller.admin.demo.vo.DemoPageReqVO;
import cn.iocoder.yudao.module.skeleton.controller.admin.demo.vo.DemoSaveReqVO;
import cn.iocoder.yudao.module.skeleton.dal.dataobject.demo.DemoDO;
import cn.iocoder.yudao.module.skeleton.dal.mysql.demo.DemoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.skeleton.enums.ErrorCodeConstants.*;

/**
 * 示例 Demo Service 实现类
 *
 * 说明：
 * 1. ServiceImpl 实现类存放在 service/{业务} 目录下（与接口同目录）
 * 2. 类名格式：{业务}ServiceImpl，例如 DemoServiceImpl、UserServiceImpl
 * 3. 注解要求：
 *    - @Service：注册为 Spring Bean
 *    - @Validated：开启方法级别的参数校验（配合 @Valid 使用）
 * 4. 注入 Mapper 使用 @Resource 注解（而非 @Autowired）
 * 5. 业务流程（以创建为例）：
 *    a. 校验业务规则（如名称唯一性）
 *    b. 使用 BeanUtils.toBean() 将 ReqVO 转为 DO
 *    c. 调用 Mapper 方法持久化
 *    d. 返回结果
 * 6. 错误处理：使用 throw exception(ErrorCode) 抛出业务异常
 *    - exception() 是 ServiceExceptionUtil.exception() 的静态导入
 *    - ErrorCode 定义在 enums/ErrorCodeConstants 中
 *
 * @author skeleton
 */
@Service
@Validated
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoMapper demoMapper;

    @Override
    public Long createDemo(DemoSaveReqVO createReqVO) {
        // 1. 校验名称唯一
        validateDemoNameUnique(null, createReqVO.getName());

        // 2. 插入数据库
        DemoDO demo = BeanUtils.toBean(createReqVO, DemoDO.class);
        demoMapper.insert(demo);

        // 3. 返回编号
        return demo.getId();
    }

    @Override
    public void updateDemo(DemoSaveReqVO updateReqVO) {
        // 1. 校验存在
        validateDemoExists(updateReqVO.getId());
        // 2. 校验名称唯一
        validateDemoNameUnique(updateReqVO.getId(), updateReqVO.getName());

        // 3. 更新数据库
        DemoDO updateObj = BeanUtils.toBean(updateReqVO, DemoDO.class);
        demoMapper.updateById(updateObj);
    }

    @Override
    public void deleteDemo(Long id) {
        // 1. 校验存在
        validateDemoExists(id);
        // 2. 删除（逻辑删除，由 BaseDO 的 @TableLogic 字段自动处理）
        demoMapper.deleteById(id);
    }

    @Override
    public void deleteDemoList(List<Long> ids) {
        // 直接批量删除（如需校验存在性，可逐个调用 validateDemoExists）
        demoMapper.deleteByIds(ids);
    }

    @Override
    public DemoDO getDemo(Long id) {
        return demoMapper.selectById(id);
    }

    @Override
    public PageResult<DemoDO> getDemoPage(DemoPageReqVO pageReqVO) {
        return demoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DemoDO> getDemoListByStatus(Integer status) {
        return demoMapper.selectListByStatus(status);
    }

    // ========== 校验方法 ==========

    /**
     * 校验示例是否存在
     */
    private void validateDemoExists(Long id) {
        if (id == null) {
            return;
        }
        if (demoMapper.selectById(id) == null) {
            throw exception(DEMO_NOT_EXISTS);
        }
    }

    /**
     * 校验名称的唯一性
     *
     * @param id 当前记录的 id（更新时传入，新建时传 null）
     * @param name 名称
     */
    private void validateDemoNameUnique(Long id, String name) {
        DemoDO demo = demoMapper.selectByName(name);
        if (demo == null) {
            return;
        }
        // 如果 id 为空，说明是新建，直接报错
        if (id == null) {
            throw exception(DEMO_NAME_DUPLICATE, name);
        }
        // 如果 id 不同，说明名称已被其他记录使用
        if (!demo.getId().equals(id)) {
            throw exception(DEMO_NAME_DUPLICATE, name);
        }
    }

}
