# 工具说明

此工具是为了更加方便的在linux环境下使v2ray客户端而开发的。此工具是对v2ray-core的包装，使其更便于linux环境使用，所以需要依赖v2Ray-core，请在https://github.com/v2ray/v2ray-core/releases/下载最新版本

# 使用方法 

1. 安装oracle-jre 1.8或者更高版本，其中使用到Base64解密方法，依赖于oracle-jre，openjdk没有该方法

2. 使用java -jar v2ray-java-cli-x.x.jar 运行即可

3. 在命令行中使用以下命令设置环境变量
```shell
# 设置v2ray的运行地址，默认情况下你可以将v2ray可执行文件的路径加入PATH环境变量即可
> set v2ray.path=/path/to/v2ray

# 设置是否自动启动，默认为否
> set auto.start=true

# 设置代理的协议，默认socks
> set proxoy.protocal=socks

# 设置本地端口,默认1080
> set local.port=1080

# 保存配置
> save
```

4. 订阅服务，设置购买的v2ray服务供应商提供的订阅更新地址

```shell
#设置订阅,更新成功后会自动
> sub http://url/to/your/sub

# 保存服务器信息和配置
> save
``` 


5. 添加服务器
``` shell
# 添加服务器
> add vmess://vmess-server-url

# 保存服务器信息和配置
> save
```

6. 启动服务器
```
# 显示服务器列表
> list

# 启动到12编号的服务器代理
> start 12
```

7. 其他
```
# 载入保存的配置文件，默认.config
> load

# 关闭服务
> close

# 退出命令行(v2ray的代理服务也会自动关闭)
> exit

```



# 遗留问题
* 关闭java后，v2ray服务也自动关闭了

需要一直开启java的命令行

* 无法自动开启系统代理，java无法设置系统环境变量

需要手动使用SwitchyOmega或者设置系统的代理配置