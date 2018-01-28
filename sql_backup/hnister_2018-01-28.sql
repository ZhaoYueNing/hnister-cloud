# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.20)
# Database: hnister
# Generation Time: 2018-01-28 06:01:29 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table t_college
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_college`;

CREATE TABLE `t_college` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL DEFAULT '' COMMENT '学院名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table t_news
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_news`;

CREATE TABLE `t_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `content` text NOT NULL,
  `author` varchar(30) DEFAULT NULL,
  `post_date` date NOT NULL,
  `module_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `module_id` (`module_id`),
  CONSTRAINT `t_news_ibfk_1` FOREIGN KEY (`module_id`) REFERENCES `t_news_module` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_news` WRITE;
/*!40000 ALTER TABLE `t_news` DISABLE KEYS */;

INSERT INTO `t_news` (`id`, `title`, `content`, `author`, `post_date`, `module_id`)
VALUES
	(11,'@Transactional','<p>init content<span style=\"color: #808000; background-color: #ddffc5; font-family: \'Source Code Pro\'; font-size: 12.8pt;\">@Transactional</span></p>\n<pre style=\"background-color: #ddffc5; font-family: \'Source Code Pro\'; font-size: 12.8pt;\"><span style=\"color: #808000;\">&nbsp;</span></pre>','@Transactional','2018-01-10',NULL),
	(14,'DEMOBBBBB','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(15,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(16,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(22,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(24,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(25,'DEMO2345','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(26,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(27,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(28,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(29,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(30,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(31,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(32,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(33,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(34,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(35,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(36,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(37,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(38,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(39,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(40,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(41,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(43,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(44,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(45,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(46,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(47,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(48,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(49,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(50,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(51,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(52,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(53,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(54,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(55,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(56,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(57,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(58,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(59,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(60,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(61,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(62,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(63,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(64,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(65,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(66,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(67,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(68,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(69,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(70,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(71,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(72,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',17),
	(74,'这是一篇测试文章','<article style=\"box-sizing: border-box; box-shadow: rgba(0, 0, 0, 0.05) 0px 2px 4px 0px; background-color: #ffffff; padding: 20px 0px; color: #333333; font-family: \'PingFang SC\', \'Microsoft YaHei\', SimHei, Arial, SimSun; font-size: 16px;\">\n<div id=\"article_content\" class=\"article_content csdn-tracking-statistics tracking-click\" style=\"box-sizing: border-box; margin: 0px 0px 30px; padding: 20px 30px 0px; color: #454545;\" data-mod=\"popu_519\" data-dsm=\"post\">\n<div class=\"markdown_views\" style=\"box-sizing: border-box; margin: 0px; padding: 0px;\">\n<p style=\"box-sizing: border-box; margin: 0px 0px 16px; padding: 0px; color: #4f4f4f; line-height: 26px; text-align: justify;\">从昨天开始使用IDEA开始就一直在搭建java环境，许久没有使用过java，刚开始有些生疏，先建了一个最简单的类：test.java ，可是运行的时候出现 错误：找不到或无法加载主类 。</p>\n<p style=\"box-sizing: border-box; margin: 0px 0px 16px; padding: 0px; color: #4f4f4f; line-height: 26px; text-align: justify;\">在网上找了好久资料，都是环境变量的问题，我在我的window命令行下看了一下java 和javac 两个均是1.7 而且可以正常的编译和运行。排除了java 环境的问题，只能是IDEA环境的问题，在项目的project structure 中，先将项目指定JDK ，然后在项目的PATH 中，将path修改为跟当前项目在一个路径下就可以了&nbsp;<br style=\"box-sizing: border-box;\" /><img style=\"box-sizing: border-box; border: 0px; vertical-align: middle; outline: 0px; margin: 24px 0px; max-width: 100%;\" title=\"\" src=\"http://img.blog.csdn.net/20170607114152084?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzEzODI5MjE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"\" /></p>\n</div>\n</div>\n</article>\n<div class=\"article_copyright\" style=\"box-sizing: border-box; margin: -10px 0px 0px; padding: 10px 20px 30px 30px; box-shadow: rgba(0, 0, 0, 0.05) 0px 2px 4px 0px; font-size: 14px; color: #788087; clear: both; overflow: hidden; background-color: #ffffff; font-family: \'PingFang SC\', \'Microsoft YaHei\', SimHei, Arial, SimSun;\">&nbsp;</div>','赵岳宁','2018-01-23',17),
	(75,'中国男足取得世界杯冠军','<p>这是不可能的事情</p>','赵岳宁','2018-01-24',NULL),
	(76,'中国男足取得世界杯冠军','<p>这是不可能的事情</p>','赵岳宁','2018-01-24',NULL),
	(77,'中国男足取得世界杯冠军','<p>这是不可能的事情</p>','赵岳宁','2018-01-24',NULL),
	(78,'中国男足取得世界杯冠军','<p>这是不可能的事情</p>','赵岳宁','2018-01-24',NULL),
	(79,'abc','init content','abc','2018-01-24',NULL),
	(80,'abc','init content','abc','2018-01-24',NULL),
	(81,'abc','init content','abc','2018-01-24',NULL),
	(82,'abc','init content','abc','2018-01-24',NULL),
	(83,'abc','init content','abc','2018-01-24',NULL);

/*!40000 ALTER TABLE `t_news` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_news_module
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_news_module`;

CREATE TABLE `t_news_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `number` bigint(20) DEFAULT '0' COMMENT '文章数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_news_module` WRITE;
/*!40000 ALTER TABLE `t_news_module` DISABLE KEYS */;

INSERT INTO `t_news_module` (`id`, `name`, `remark`, `number`)
VALUES
	(17,'DEMO','',61);

/*!40000 ALTER TABLE `t_news_module` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_resource
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_resource`;

CREATE TABLE `t_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `method` int(4) unsigned NOT NULL COMMENT 'GET,\n    HEAD,\n    POST,\n    PUT,\n    PATCH,\n    DELETE,\n    OPTIONS,\n    TRACE;',
  `url` varchar(100) NOT NULL DEFAULT '',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '资源状态 0：白名单不被权限监控 1：被监控 2：废弃',
  `group` int(11) unsigned DEFAULT NULL COMMENT '资源组 便于后台管理',
  PRIMARY KEY (`id`),
  UNIQUE KEY `method` (`method`,`url`),
  KEY `group` (`group`),
  CONSTRAINT `t_resource_ibfk_1` FOREIGN KEY (`group`) REFERENCES `t_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_resource` WRITE;
/*!40000 ALTER TABLE `t_resource` DISABLE KEYS */;

INSERT INTO `t_resource` (`id`, `name`, `method`, `url`, `status`, `group`)
VALUES
	(1,'add news',0,'/hnist-news-service/news',0,NULL);

/*!40000 ALTER TABLE `t_resource` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_resource_group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_resource_group`;

CREATE TABLE `t_resource_group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table t_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL DEFAULT '',
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;

INSERT INTO `t_role` (`id`, `name`, `remark`)
VALUES
	(1,'admin','超级管理员权限'),
	(2,'general','普通用户');

/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_role_resource
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_role_resource`;

CREATE TABLE `t_role_resource` (
  `role_id` int(11) unsigned NOT NULL,
  `resource_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`),
  KEY `resource_id` (`resource_id`),
  CONSTRAINT `t_role_resource_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `t_role_resource_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_role_resource` WRITE;
/*!40000 ALTER TABLE `t_role_resource` DISABLE KEYS */;

INSERT INTO `t_role_resource` (`role_id`, `resource_id`)
VALUES
	(1,1);

/*!40000 ALTER TABLE `t_role_resource` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_role_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_role_user`;

CREATE TABLE `t_role_user` (
  `role_id` int(11) unsigned NOT NULL,
  `username` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_id`,`username`),
  KEY `username` (`username`),
  CONSTRAINT `t_role_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `t_role_user_ibfk_2` FOREIGN KEY (`username`) REFERENCES `t_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_role_user` WRITE;
/*!40000 ALTER TABLE `t_role_user` DISABLE KEYS */;

INSERT INTO `t_role_user` (`role_id`, `username`)
VALUES
	(1,'zhaozhaozhao2');

/*!40000 ALTER TABLE `t_role_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_specialty
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_specialty`;

CREATE TABLE `t_specialty` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `college_id` int(11) unsigned NOT NULL,
  `name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `college_id` (`college_id`),
  CONSTRAINT `t_specialty_ibfk_1` FOREIGN KEY (`college_id`) REFERENCES `t_college` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table t_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `zlass_id` int(11) unsigned DEFAULT NULL,
  `name` varchar(40) NOT NULL DEFAULT '',
  `birth` date DEFAULT NULL,
  `gender` int(2) DEFAULT '1' COMMENT '1 man 0 woman',
  `status` smallint(2) NOT NULL DEFAULT '0' COMMENT '校友账号状态 0：未验证 1：已验证',
  `city` varchar(11) DEFAULT NULL COMMENT '当前所在地 长沙',
  `salt` varchar(100) NOT NULL DEFAULT '' COMMENT '密码加密盐',
  `password` varchar(100) NOT NULL DEFAULT '',
  `username` varchar(50) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '账号创建时间',
  PRIMARY KEY (`username`),
  KEY `zlass_id` (`zlass_id`),
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`zlass_id`) REFERENCES `t_zlass` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;

INSERT INTO `t_user` (`zlass_id`, `name`, `birth`, `gender`, `status`, `city`, `salt`, `password`, `username`, `create_time`)
VALUES
	(NULL,'zhao',NULL,1,0,'changsha','1516953677817','ac439b2a1b74e728f6a7ff34be3329d6a78a4d014489d4d1b798213233cb75ec','zhaoyuening','2018-01-26 16:01:36'),
	(NULL,'zhao',NULL,NULL,0,NULL,'1516960175517','21e6056bcbd02e407b1e71d38fd8de52e50c697854dcdfa56cae8edd8320d498','zhaozhaozhao','2018-01-26 17:49:40'),
	(NULL,'zhao',NULL,NULL,0,NULL,'1516962010803','438de76e78b8cc4dffbbedcd85ee8c64cefe80b4294e518cada9d1cdf1cae9d7','zhaozhaozhao2','2018-01-26 18:20:10');

/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_zlass
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_zlass`;

CREATE TABLE `t_zlass` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL DEFAULT '' COMMENT '名称',
  `specialty_id` int(11) unsigned NOT NULL COMMENT '专业',
  `grade` varchar(40) NOT NULL DEFAULT '' COMMENT '年级 如2014级',
  PRIMARY KEY (`id`),
  KEY `specialty_id` (`specialty_id`),
  CONSTRAINT `t_zlass_ibfk_1` FOREIGN KEY (`specialty_id`) REFERENCES `t_specialty` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
