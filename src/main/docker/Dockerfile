FROM centos:centos7
MAINTAINER jnn
# 1.准备工作创建文件夹
RUN \
    mkdir -p /usr/local \
    && mkdir -p /root/ddos/html \
    && yum -y install vim* \
    && yum -y install lrzsz
# 添加文件, ADD方式会自动解压压缩包
ADD ./* /usr/local/
# 安装redis
RUN \
    yum -y install gcc gcc-c++ kernel-devel tcl make pcre pcre-devel zlib zlib-devel openssl openssl-devel \
    && cd /usr/local \
    && cd redis-4.0.9 && make && make install PREFIX=/usr/local/redis \
    && ln -s /usr/local/redis/bin/* /usr/local/bin/ \
    && mkdir /var/redis && cd /var/redis && mkdir data log run \
    && cp /usr/local/redis.conf /etc \
    && rm -rf /usr/local/redis-4.0.9 && rm -rf /usr/local/redis.conf \
    && yum clean all 
# 安装nginx
RUN \
    cd /usr/local \
    && cd nginx-1.16.0 \
    && ./configure --with-http_stub_status_module \
    && make && make install PREFIX=/usr/local/nginx \
    && mv /usr/local/nginx/conf/nginx.conf /usr/local/nginx/conf/nginx.conf.bak \
    && cp /usr/local/nginx.conf /usr/local/nginx/conf \
    && ln -s /usr/local/nginx/sbin/* /usr/local/bin/ \
    && rm -rf /usr/local/nginx-1.16.0 && rm -rf /usr/local/nginx.conf \
    && yum clean all 
# 配置java环境变量
ENV JAVA_HOME /usr/local/jdk1.8.0_171
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV PATH $PATH:$JAVA_HOME/bin
ENV TZ=Asia/Shanghai
ENV LANG en_US.utf8
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["/bin/bash"]
CMD ["/usr/local/startService.sh"]
