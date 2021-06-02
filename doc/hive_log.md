1.问题：  
hive insert数据时Error during job, obtaining debugging information 以及beyond physical memory limits...  
报错log：
```aidl
2021-06-02T14:21:27,068  INFO [83d6c7df-4e5f-424f-8652-164fce84d38a main] exec.Task: 2021-06-02 14:21:27,061 Stage-1 map = 0%,  reduce = 0%
2021-06-02T14:21:27,070  WARN [83d6c7df-4e5f-424f-8652-164fce84d38a main] mapreduce.Counters: Group org.apache.hadoop.mapred.Task$Counter is deprecated. Use org.apache.hadoop.mapreduce.TaskCounter instead
2021-06-02T14:21:27,081 ERROR [83d6c7df-4e5f-424f-8652-164fce84d38a main] exec.Task: Ended Job = job_1622614773321_0001 with errors
2021-06-02T14:21:27,082 ERROR [Thread-26] exec.Task: Error during job, obtaining debugging information...
2021-06-02T14:21:27,082  INFO [Thread-26] Configuration.deprecation: mapred.job.tracker is deprecated. Instead, use mapreduce.jobtracker.address
2021-06-02T14:21:27,104  INFO [83d6c7df-4e5f-424f-8652-164fce84d38a main] impl.YarnClientImpl: Killed application application_1622614773321_0001
2021-06-02T14:21:27,116  INFO [83d6c7df-4e5f-424f-8652-164fce84d38a main] reexec.ReOptimizePlugin: ReOptimization: retryPossible: false
2021-06-02T14:21:27,116 ERROR [83d6c7df-4e5f-424f-8652-164fce84d38a main] ql.Driver: FAILED: Execution Error, return code 2 from org.apache.hadoop.hive.ql.exec.mr.MapRedTask
```
解决办法：
1.执行sql前输入：
```aidl
set hive.exec.mode.local.auto=true;
```
2.在hive-site.xml中添加：
```aidl
<property>
<name>hive.exec.mode.local.auto</name>
<value>true</value>
</property>
```
重启hive可能会报错，是因为进入了安全模式，把安全模式关闭之后再进入hive就好，关闭安全模式命令如下：
```aidl
hadoop dfsadmin -safemode leave
```