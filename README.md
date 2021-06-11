## spring-security-oauth2-demo
spring security oauth2 demo

## 技术栈
``` 
spring boot 2.3.10
spring security oauth2 2.3.8
spring data jpa
```

## 更新记录
> 2021-06-10 实现自定义spring security oauth2登录
>> 基于jdbc数据库实现密码模式
>
> 2021-06-11 集成文档工具knife4j
>> 目前用的老版本1.9x，蓝色界面；后面可更新到新版2.x,黑色界面 -- 更新请参考https://blog.csdn.net/u010192145/article/details/103556581

## 部署
创建数据库test -> 执行oauth2.sql  -> 启动项目即可

文档地址：http://192.168.2.13:18000/doc.html

## 接口文档
1、用户密码登录

##### 简要描述

- WEB端商家登录接口
- 此接口发放的token`有效期为24小时`
- 访问受保护资源需要再请求header中带上access_token
#####Headers:
Authorization:bearer {access_token}

##### 请求URL
- ` http://127.0.0.1:18000/auth/login`
  
##### 请求方式
- POST 

##### 参数

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|username |是  |string |用户名   |
|password |是  |string | 密码    |

##### 请求示例 
```
{
    "username":"17783715544",
    "password":"123456"
}
```

##### 返回参数说明 

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|status |是|int   |0 - 成功, 其他为失败 |
|msg|是|string|接口状态描述信息|
|data|是|array|返回数据, 参考**LoginResponse**对象|
|||||
|access_token|是|string|访问接口时携带的token|
|token_type|是|string|token类型,固定为bearer|
|refresh_token|是|string|tokend有效期内刷新token时使用|
|expires_in|是|int|token有效期|
|scope|是|string|用户权限作用域|
|user_id|是|string|用户guid|
|isMerchant|是|bool|是否为商家账号|
|access_rights|是|array|用户权限|
|hasPhoneNumber|是|string|是否绑定电话号码|
|hasOpenId|是|string|是否绑定第三方登录|

##### 返回示例 
``` 
{
    "status": 0,
    "msg": "success",
    "data": {
        "access_token": "7da043fb-7c1a-4efa-ad38-7dae362bd316",
        "token_type": "bearer",
        "refresh_token": "90042019-5467-4a6f-bff9-c7e8508bec45",
        "expires_in": 81058,
        "scope": "all",
        "name": "大帅比",
        "username": "17783715544"
    }
}
```
##### 错误码

- 70000 -登录失败


