## 搭建一个私有网络教程

示例中搭建一个一个SR节点、一个FullNode节点

### 修改配置：
supernode_config.conf
localwitness:The private key of witness address
genesis.block.witnesses:Witness address


fullnode_config.conf
genesis.block.witnesses:Witness address



### 启动参数：
1、SR ：
--witness  -c /Users/xiaochangbai/workspaces/idea/java-tron/private-net/sr/supernode_config.conf

2、FullNode
-c /Users/xiaochangbai/workspaces/idea/java-tron/private-net/fullnote/fullnode_config.conf

注意事项：记得修改工作目录，idea在Run/Debug Configuration中设置 working workspace，SR和FullNode设置成不同的就是

### wallet连接

修改配置文件
fullnode.ip.list=["127.0.0.1:16659"]
