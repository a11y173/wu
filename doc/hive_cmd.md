1.启动hive-server2
```aidl
$HIVE_HOME/bin/hive --service hiveserver2 &
```
查看hive-server2是否启动
```aidl
netstat -nl | grep 10000
```
beeline 链接
```aidl
beeline -u  jdbc:hive2://localhost:10000 -n root -p root
```