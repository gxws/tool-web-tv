# tool-web-tv
电视端项目工具


版本变更说明
---

### 1.0.0
从url获取参数，将参数以固定格式放入session，给jsp页面使用el表达式调用。<br>
根据页面传递的参数，判断机顶盒类型，指定返回jsp路径。<br>

### 1.1.0
去除将用户信息TvUserInfo保存至http session中。<br>

添加tool-common依赖，版本号为1.2.0-RELEASE。<br>
添加新的机顶盒访问参数WebTvParam，替代原有的TvUserInfo。<br>
添加使用注解的方式对机顶盒访问参数WebTvParam声明处理方式。<br>
添加机顶盒访问参数WebTvParam验证缺少参数时的异常WebTvParameterMissingException。<br>
添加机顶盒类型判断，02和03由广电连接传，再经由http head user-agent判断01和02类型。<br>

修改interceptor，将信息不完全的请求拦截报错。<br>
修改机顶盒信息对象获取方式，从HttpServletRequest对象调用getAttribute方法，attr名称为WebTvParam.ATTR_NAME。jsp使用el获取具体参数的方式为${webTv.stbId}。<br>
修改jsp使用el获取url参数的参数名，参数名修改为WebTvParam.URL_PARAM_NAME，调用方式为${webTv.url}。<br>

