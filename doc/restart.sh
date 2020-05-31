#!/usr/bin/env bash
g
#备份文件
date=$(date +%Y_%m_%d-%H%M%S)
mv /home/admin/admin.jar /home/admin/admin.jar.$date

mv /home/allisonedev/opendoc/upload/opendoc.jar /home/allisonedev/opendoc/opendoc.jar

pId=$(ps -ef |grep admin.jar |grep -w 'admin'|grep -v 'grep'|awk '{print $2}')
echo "当前 id 为 $pId" 
kill -9 $pId # 杀掉原ui进程
sleep 5
nohup java -jar /home/admin/admin.jar &
