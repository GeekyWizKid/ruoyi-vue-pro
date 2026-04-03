package cn.iocoder.yudao.module.skeleton.dal.mysql.demo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.skeleton.controller.admin.demo.vo.DemoPageReqVO;
import cn.iocoder.yudao.module.skeleton.dal.dataobject.demo.DemoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 示例 Demo Mapper
 *
 * 说明：
 * 1. Mapper 接口存放在 dal/mysql/{业务} 目录下
 * 2. 继承 BaseMapperX<DO>，自动获得 CRUD 基础方法（selectById、insert、updateById、deleteById 等）
 * 3. 使用 default 方法实现自定义查询，避免写 XML 文件
 * 4. 使用 LambdaQueryWrapperX 构建查询条件：
 *    - likeIfPresent：非空时模糊匹配
 *    - eqIfPresent：非空时精确匹配
 *    - inIfPresent：非空时 IN 匹配
 *    - betweenIfPresent：非空时范围匹配
 *    - orderByDesc / orderByAsc：排序
 * 5. 分页查询使用 selectPage(PageParam, QueryWrapper) 方法
 * 6. 单条查询使用 selectOne(SFunction, Object) 方法
 * 7. 类名格式：{业务}Mapper，例如 DemoMapper、UserMapper
 *
 * @author skeleton
 */
@Mapper
public interface DemoMapper extends BaseMapperX<DemoDO> {

    /**
     * 分页查询示例列表
     */
    default PageResult<DemoDO> selectPage(DemoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DemoDO>()
                .likeIfPresent(DemoDO::getName, reqVO.getName())
                .eqIfPresent(DemoDO::getStatus, reqVO.getStatus())
                .orderByDesc(DemoDO::getId));
    }

    /**
     * 根据名称查询（用于唯一性校验）
     */
    default DemoDO selectByName(String name) {
        return selectOne(DemoDO::getName, name);
    }

    /**
     * 根据状态查询列表
     */
    default List<DemoDO> selectListByStatus(Integer status) {
        return selectList(DemoDO::getStatus, status);
    }

}
