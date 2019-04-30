#项目结构  
##包结构
`com.myMall.common`  
>主要用于存放公共的类，如常量、网络响应等

`com.myMall.pojo`
>数据库表结构对应的javabean，由mybatis-generator自动生成

`com.myMall.dao`
>操作数据库的一系列接口，由mybatis-generator自动生成，具体实现在resources/mappers中的.xml中

`com.myMall.service`
>实现一系列业务逻辑，通过dao层和数据库进行交互。

`com.myMall.controller`
>通过路由，来响应前端的请求，不直接和数据库进行交互，业务中对数据的验证通过service层进行。

`com.myMall.util`
>工具包

`com.myMall.vo`
>viewObject 供前端展示的对象

##配置文件
