#!/bin/sh

##rm -f tpid

APP_NAME=lecloud-server-manager
APP_JAR=$APP_NAME"-0.0.1-SNAPSHOT.jar"

##nohup命令提交作业，那么在缺省情况下该作业的所有输出都被重定向到一个名为nohup.out的文件中，除非另外指定了输出文件。这里指定输出文件在为./mapping-service.log
nohup java -jar $APP_JAR > $APP_NAME".log" 2>&1 &

##echo $! > $APP_NAME".tpid"

echo $APP_NAME Start Success!