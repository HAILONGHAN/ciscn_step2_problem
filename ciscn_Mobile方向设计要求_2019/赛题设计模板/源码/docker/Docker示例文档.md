## Docker 环境示例

### 1. Dockerfile样例

```docker
FROM wnameless/mysql-phpmyadmin:latest
RUN rm -rf /var/www/
COPY ./www /var/www
COPY ./flag /flag
WORKDIR /var/www/

# 支持SSH
RUN rm -f /etc/service/sshd/down
RUN sed -ri 's/^#?PermitRootLogin\s+.*/PermitRootLogin yes/' /etc/ssh/sshd_config

# 添加普通用户ciscn与设置密码
RUN groupadd ciscn && \
	useradd -g ciscn ciscn -m && \
	password=$(openssl passwd -1 -salt 'abcdefg' '123456') && \
	sed -i 's/^ciscn:!/ciscn:'$password'/g' /etc/shadow
	
# 修改权限
WORKDIR /var/www/
RUN chown -R ciscn:ciscn . && \
	chmod -R 750 .
```

### 2. start.sh样例

```sh

#!/bin/bash

# 启动服务，例如apache2
service apache2 start
# 为了适应各种docker版本，mysql的启动命令建议如下（mysqld除外）
find /var/lib/mysql -type f -exec touch {} \; && service mysql start
```

### 3. docker-compose.yml 示例

最好将数据库等服务与web服务放在同一容器中

```
version: "3"
services:
    web:    # 服务名
        build: ./web    # web服务 Dockerfile所在目录
        ports:  # 容器中80端口映射到本机8233端口
            - 8233:80
        volumes:    # 目录挂载，题目开发完成可删掉
            - ./web:/var/www/html 
```

## 镜像编译与启动

```
docker-compose up -d --build # 镜像编译 与 后台启动
docker-compose down # 停止容器并删除
```

如没有docker-compose.yml，进入Dockerfile所在目录

```
docker build -t android_web:php5.3 . 
docker run -d -p 8080:80 android_web:php5.3
docker exec -it web1 bash # 进入容器
docker stop web1 # 停止容器
docker rm web1 # 删除容器
```