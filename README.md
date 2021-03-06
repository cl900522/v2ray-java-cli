# 工具说明

此工具是为了更加方便的在linux环境下使v2ray客户端而开发的。此工具是对v2ray-core的包装，使其更便于linux环境使用，所以需要依赖v2Ray-core，请在https://github.com/v2ray/v2ray-core/releases/ 下载最新版本

# 使用方法 

1. 安装oracle-jre 或者openjdk 1.8或者更高版本

2. 使用java -jar v2ray-java-cli-x.x.jar 运行即可

3. 在命令行中使用以下命令设置环境变量

```shell
# 设置v2ray的运行地址，默认情况下你可以将v2ray可执行文件的路径加入PATH环境变量即可
> set v2ray.path=/path/to/v2ray

# 设置是否自动启动，默认为否
> set auto.start=true

# 设置代理的协议，默认socks
> set proxoy.protocal=socks

# 设置socks5端口,默认1080
> set local.socksPort=1080

# 设置http代理端口,默认1081
> set local.httpPort=1081

# 保存配置
> save
```

4. 订阅服务，设置购买的v2ray服务供应商提供的订阅更新地址

```shell
#设置订阅,更新成功后会自动，默认为 default分组
> sub http://url/to/your/sub

#设置订阅并将订阅的服务器归类为mygroup分组
> sub mygroup http://url/to/your/sub

# 保存服务器信息和配置
> save
``` 

5. 添加服务器

``` shell
# 添加服务器，归类为manual分组
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

# 保存配置选项
> save
```

7. 移除服务器或分组
```
#删除所有服务器和分组
> del *

#删除mygroup的分组订阅以及服务器
> del mygroup

#删除编号12的服务器
> del 12

#循环删除
> del 12 mygroup1 mygroup2 

```

8. 其他

```
# 载入保存的配置文件，默认.config
> load

# 关闭服务
> close

# 退出命令行（v2ray服务继续运行，可以使用close命令关闭）
> exit

```

8. 自动启动服务器
````````
建议你第一次启动后，设置自动启动参数auto.start=true，使用save命令保存配置（所有配置都已保存在pwd目录的.config文件中）

在开机自启动脚本增加一下命令就可以自动载入配置文件中服务器列表和选择的服务器
```

nohup java -jar v2ray-java-cli-x.x.jar /path/to/your/.config/file &

```


# 遗留问题

* 无法自动开启系统代理，java无法设置系统环境变量

需要手动使用SwitchyOmega或者设置系统的代理配置
