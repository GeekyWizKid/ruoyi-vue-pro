# yudao-module-skeleton：AI 开发示例骨架

## 📌 模块说明

本模块是一个 **AI 开发参考骨架（Skeleton）**，用于指导 AI 工具（如 GitHub Copilot、Cursor 等）按照本项目的规范生成代码。

本模块包含一个完整的 CRUD 示例（Demo），覆盖了项目开发中最常见的代码模式。AI 在为本项目新增业务模块时，应参考本模块中的代码结构、命名规范和注解用法。

---

## 📁 目录结构

```
yudao-module-skeleton/
├── pom.xml                                          # Maven 依赖配置
├── sql/
│   └── skeleton_demo.sql                            # 建表 SQL 示例
├── README.md                                        # 本文件
└── src/main/java/cn/iocoder/yudao/module/skeleton/
    ├── controller/admin/demo/                       # 【Controller 层】REST API 接口
    │   ├── DemoController.java                      #   控制器：定义接口路由、权限、参数校验
    │   └── vo/                                      #   VO（View Object）：前端交互的数据结构
    │       ├── DemoSaveReqVO.java                   #     创建/修改请求体
    │       ├── DemoPageReqVO.java                   #     分页查询请求体（继承 PageParam）
    │       ├── DemoRespVO.java                      #     详情响应体
    │       └── DemoSimpleRespVO.java                #     精简响应体（用于下拉选项）
    ├── service/demo/                                # 【Service 层】业务逻辑
    │   ├── DemoService.java                         #   接口定义
    │   └── DemoServiceImpl.java                     #   实现类：校验、转换、持久化
    ├── dal/                                         # 【DAL 层】数据访问
    │   ├── dataobject/demo/                         #   DO（Data Object）：数据库实体
    │   │   └── DemoDO.java                          #     映射 skeleton_demo 表
    │   └── mysql/demo/                              #   Mapper 接口（MyBatis Plus）
    │       └── DemoMapper.java                      #     继承 BaseMapperX，自定义查询
    └── enums/                                       # 【枚举层】错误码、常量
        └── ErrorCodeConstants.java                  #   模块错误码定义
```

---

## 🏗 分层架构

```
请求 → Controller → Service → Mapper → 数据库
响应 ← Controller ← Service ← Mapper ← 数据库
```

| 层         | 职责                           | 输入/输出                    |
|------------|-------------------------------|------------------------------|
| Controller | 路由、权限、参数校验、VO 转换    | ReqVO → DO → RespVO         |
| Service    | 业务规则校验、数据转换、编排      | ReqVO → DO                  |
| Mapper     | 数据库 CRUD、查询条件构建        | DO ↔ 数据库                 |

---

## 📐 命名规范速查

| 类型         | 格式                    | 示例                     | 位置                   |
|--------------|------------------------|--------------------------|------------------------|
| 数据库实体    | `{业务}DO`              | `DemoDO`                | `dal/dataobject/`      |
| 创建/修改请求 | `{业务}SaveReqVO`       | `DemoSaveReqVO`         | `controller/.../vo/`   |
| 分页请求      | `{业务}PageReqVO`       | `DemoPageReqVO`         | `controller/.../vo/`   |
| 列表请求      | `{业务}ListReqVO`       | `DemoListReqVO`         | `controller/.../vo/`   |
| 详情响应      | `{业务}RespVO`          | `DemoRespVO`            | `controller/.../vo/`   |
| 精简响应      | `{业务}SimpleRespVO`    | `DemoSimpleRespVO`      | `controller/.../vo/`   |
| 跨模块 DTO    | `{业务}RespDTO`         | `DemoRespDTO`           | `api/.../dto/`         |
| Service 接口  | `{业务}Service`         | `DemoService`           | `service/`             |
| Service 实现  | `{业务}ServiceImpl`     | `DemoServiceImpl`       | `service/`             |
| Mapper       | `{业务}Mapper`          | `DemoMapper`            | `dal/mysql/`           |
| 错误码       | `ErrorCodeConstants`    | `ErrorCodeConstants`    | `enums/`               |
| 数据库表      | `{模块}_{业务}`         | `skeleton_demo`         | `sql/`                 |

---

## 📝 核心编码规范

### 1. Controller 层
- 使用 `@Tag(name = "管理后台 - {业务}")` 标记 Swagger 分组
- 使用 `@Operation(summary = "xxx")` 描述每个接口
- 使用 `@PreAuthorize("@ss.hasPermission('{模块}:{业务}:{操作}')")` 做权限校验
- 所有响应使用 `CommonResult<T>` 包装，通过 `success()` 静态方法返回
- 使用 `BeanUtils.toBean()` 进行 DO → VO 转换

### 2. Service 层
- 接口 + 实现分离（`DemoService` + `DemoServiceImpl`）
- 实现类使用 `@Service` + `@Validated` 注解
- 使用 `throw exception(ErrorCode)` 抛出业务异常
- 创建/更新前先做业务校验（存在性、唯一性等）
- 使用 `BeanUtils.toBean()` 进行 VO → DO 转换

### 3. DAL 层
- DO 继承 `TenantBaseDO`（多租户）或 `BaseDO`（非多租户）
- Mapper 继承 `BaseMapperX<DO>`
- 使用 `LambdaQueryWrapperX` + `default` 方法构建查询，避免 XML
- 条件方法：`likeIfPresent`、`eqIfPresent`、`inIfPresent`、`betweenIfPresent`

### 4. VO 层
- 使用 `@Schema` 注解生成 API 文档
- 使用 JSR-303 注解做参数校验（`@NotBlank`、`@NotNull`、`@Size`、`@InEnum`）
- 创建和修改共用 `SaveReqVO`，通过 `id` 字段是否为空区分

---

## 🔧 新建业务模块的步骤

1. **建表**：在 `sql/` 目录下编写建表 SQL
2. **创建 DO**：在 `dal/dataobject/{业务}/` 下创建 DO 类，继承 `TenantBaseDO` 或 `BaseDO`
3. **创建 Mapper**：在 `dal/mysql/{业务}/` 下创建 Mapper 接口，继承 `BaseMapperX<DO>`
4. **创建 VO**：在 `controller/admin/{业务}/vo/` 下创建 `SaveReqVO`、`PageReqVO`、`RespVO` 等
5. **创建 Service**：在 `service/{业务}/` 下创建接口和实现类
6. **创建 Controller**：在 `controller/admin/{业务}/` 下创建 Controller
7. **定义错误码**：在 `enums/ErrorCodeConstants.java` 中添加业务错误码

---

## ⚠️ 注意事项

- 本模块仅作为 AI 代码生成的参考，**不包含在生产构建中**
- 实际开发时，新模块应创建为独立的 `yudao-module-{name}` 模块
- 跨模块调用通过 `api/` 目录下的接口实现（参考 `yudao-module-system` 的 `api/dept/DeptApi.java`）
