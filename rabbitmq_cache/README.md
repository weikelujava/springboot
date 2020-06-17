# springboot
All projects use the springboot architecture

#### 项目描述
- **场景描述**
  
  - 本项目使用RabiitMQ消息中间件模拟分布式项目中本地EhCache和Redis缓存的一致性操作
  - 最好的解决方案是在服务网关做IPHash一致性路由
  - 其中Nginx负载的也有路由配置(随机、定向、轮询(默认)等等)
  
- **使用技术点**
  
  - RabbitMQ消息中间件,使用Fanout模式()
  - EhCache缓存
  - Redis缓存(只是为了存放数据,缓存击穿,缓存穿透,缓存雪崩的解决方案在这里没有做出体现,可以参考(参考布隆过滤器),漏桶算法,服务降级,服务熔断,服务限流等)
  - Redisson分布式锁 (也可以使用zookeeper的分布式锁，这里测试方便就使用了Redisson分布式锁)
  
  
- **说明**

  - 1.RabbitMQ的IP (127.0.0.1:5672),访问IP (127.0.0.1:15672)
    - 用户名&密码  admin/admin
  - 2.Redis的IP (127.0.0.1:6379)
    - 未设置密码
  - 3.启动方式
    - mvn打包后,使用java -jar ...命令启动
  - 4.接口访问测试(所有请求均为GET,只是为了测试,缓存命名空间，缓存键，缓存值都在后台写死，需要根据业务逻辑做动态配置)
    1. http://127.0.0.1:1001/rabbit/send      GET请求  发送消息,并将消息写入EhCache缓存和Redis缓存
    2. http://127.0.0.1:1001/rabbit/update    GET请求  发送消息，并更新EhCache缓存和Redis缓存
    3. http://127.0.0.1:1001/rabbit/delete    GET请求  发送消息，清除EhCache缓存和Redis缓存
    4. http://127.0.0.1:1001/get              GET请求  获取数据，EhCache->Redis->Mysql,如果EhCache中没有，Redis中有，并将数据写入到EhCache
    5. http://127.0.0.1:1001/redis/delete     GET请求  直接删除Redis缓存数据
    6. http://127.0.0.1:1001/cache/delete     GET请求  直接删除EhCache缓存数据
    