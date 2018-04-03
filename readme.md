## 项目组成
- [后台管理前端项目-基于VUE](https://gitee.com/Buynow96/hnister-html)
- [前台前端项目-基于VUE](https://gitee.com/Buynow96/hnister-vue)

## 组件端口
| 服务名称 | 功能  | 端口  |
| :---:   | :-: | :-: |
| hnister-config-server | 服务配置中心 | 8888 |
| hnister-eureka-server | 服务注册中心 | 8889 |
| hnister-zuul-server | 服务代理端口 | 10000 |
| hnister-file-service | 文件服务 | 9000 |
| hnister-news-service | 新闻服务 | 9001 |
| hnister-security-service | 安全服务 | 9002 |
| hnister-schoolmate-service | 校友服务 | 9003 |
| hnister-setting-service | 基础配置服务 | 9004 |
| hnister-bbs-service | 社区论坛服务 | 9005 |


## url配置规则
|    url前缀    | 外访 | 登录 | 鉴权 |  说明        |
|    :----:    | :-:  | :-: |  :-:  | :---:        |
|/rest/pb/     |  √   |  ×  |  ×  |public开放     |
|/rest/pt/     |  √   |  √  |  √  |protected受保护|
|/rest/df/     |  √   |  √  |  ×  |default登录不鉴权|
|/rest/pv/     |  ×   |  ×  |  ×  |private私有     |
|其他           |  ×   |  ×  |  ×  |private私有     |
