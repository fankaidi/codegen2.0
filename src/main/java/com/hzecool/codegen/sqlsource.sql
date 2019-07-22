CREATE TABLE `lc_history` (
  `id` bigint(20) NOT NULL COMMENT '处理过的审批痕迹',
  `busitype` varchar(64) DEFAULT NULL COMMENT '业务类型，比如是mdt申请的代办',
  `entry_name` varchar(128) DEFAULT NULL COMMENT '节点名称，表示是那个步骤的名称',
  `userid` int(11) DEFAULT NULL COMMENT '操作人',
  `bisiid` int(11) DEFAULT NULL COMMENT '业务id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `audit_result` int(11) DEFAULT NULL COMMENT '审核结果，1是通过，-1是不通过',
  `audit_opinion` varchar(512) DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`id`)
) 、
