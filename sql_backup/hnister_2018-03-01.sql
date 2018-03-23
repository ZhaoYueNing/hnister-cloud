# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.20)
# Database: hnister
# Generation Time: 2018-03-01 08:58:00 +0000
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

LOCK TABLES `t_college` WRITE;
/*!40000 ALTER TABLE `t_college` DISABLE KEYS */;

INSERT INTO `t_college` (`id`, `name`, `remark`)
VALUES
	(1,'南湖学院','湖南理工学院南湖学院');

/*!40000 ALTER TABLE `t_college` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_content_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_content_menu`;

CREATE TABLE `t_content_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `url` varchar(200) DEFAULT NULL,
  `type` int(4) NOT NULL DEFAULT '1' COMMENT '菜单链接类型：1、不可以点击 2、内部跳转URL 3、外部URL',
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '父菜单的id 根目录id 0',
  `menu_id` int(4) NOT NULL COMMENT '菜单编号:1 首页导航栏菜单',
  `order_num` int(11) NOT NULL DEFAULT '100' COMMENT '用来排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_content_menu` WRITE;
/*!40000 ALTER TABLE `t_content_menu` DISABLE KEYS */;

INSERT INTO `t_content_menu` (`id`, `name`, `url`, `type`, `parent_id`, `menu_id`, `order_num`)
VALUES
	(1,'新闻动态','http://www.baidu.com',1,0,1,100);

/*!40000 ALTER TABLE `t_content_menu` ENABLE KEYS */;
UNLOCK TABLES;


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
  CONSTRAINT `t_news_ibfk_1` FOREIGN KEY (`module_id`) REFERENCES `t_news_module` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_news` WRITE;
/*!40000 ALTER TABLE `t_news` DISABLE KEYS */;

INSERT INTO `t_news` (`id`, `title`, `content`, `author`, `post_date`, `module_id`)
VALUES
	(15,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(16,'DEMOaaaa','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(24,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(25,'DEMO2345','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(26,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(27,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(28,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(29,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(30,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(31,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(32,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(33,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(34,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(35,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(36,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(37,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(38,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(39,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(40,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(41,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(43,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(44,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(45,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(46,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(47,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(48,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(49,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(50,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(51,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(52,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(53,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(54,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(55,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(56,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(57,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(58,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(59,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(60,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(61,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(62,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(63,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(64,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(65,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(66,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(67,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(68,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(69,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(70,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(71,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(72,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),
	(74,'这是一篇测试文章','<article style=\"box-sizing: border-box; box-shadow: rgba(0, 0, 0, 0.05) 0px 2px 4px 0px; background-color: #ffffff; padding: 20px 0px; color: #333333; font-family: \'PingFang SC\', \'Microsoft YaHei\', SimHei, Arial, SimSun; font-size: 16px;\">\n<div id=\"article_content\" class=\"article_content csdn-tracking-statistics tracking-click\" style=\"box-sizing: border-box; margin: 0px 0px 30px; padding: 20px 30px 0px; color: #454545;\" data-mod=\"popu_519\" data-dsm=\"post\">\n<div class=\"markdown_views\" style=\"box-sizing: border-box; margin: 0px; padding: 0px;\">\n<p style=\"box-sizing: border-box; margin: 0px 0px 16px; padding: 0px; color: #4f4f4f; line-height: 26px; text-align: justify;\">从昨天开始使用IDEA开始就一直在搭建java环境，许久没有使用过java，刚开始有些生疏，先建了一个最简单的类：test.java ，可是运行的时候出现 错误：找不到或无法加载主类 。</p>\n<p style=\"box-sizing: border-box; margin: 0px 0px 16px; padding: 0px; color: #4f4f4f; line-height: 26px; text-align: justify;\">在网上找了好久资料，都是环境变量的问题，我在我的window命令行下看了一下java 和javac 两个均是1.7 而且可以正常的编译和运行。排除了java 环境的问题，只能是IDEA环境的问题，在项目的project structure 中，先将项目指定JDK ，然后在项目的PATH 中，将path修改为跟当前项目在一个路径下就可以了&nbsp;<br style=\"box-sizing: border-box;\" /><img style=\"box-sizing: border-box; border: 0px; vertical-align: middle; outline: 0px; margin: 24px 0px; max-width: 100%;\" title=\"\" src=\"http://img.blog.csdn.net/20170607114152084?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzEzODI5MjE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"\" /></p>\n</div>\n</div>\n</article>\n<div class=\"article_copyright\" style=\"box-sizing: border-box; margin: -10px 0px 0px; padding: 10px 20px 30px 30px; box-shadow: rgba(0, 0, 0, 0.05) 0px 2px 4px 0px; font-size: 14px; color: #788087; clear: both; overflow: hidden; background-color: #ffffff; font-family: \'PingFang SC\', \'Microsoft YaHei\', SimHei, Arial, SimSun;\">&nbsp;</div>','赵岳宁','2018-01-23',NULL),
	(75,'中国男足取得世界杯冠军','<p>这是不可能的事情</p>','赵岳宁','2018-01-24',NULL),
	(76,'中国男足取得世界杯冠军','<p>这是不可能的事情</p>','赵岳宁','2018-01-24',NULL),
	(77,'中国男足取得世界杯冠军','<p>这是不可能的事情</p>','赵岳宁','2018-01-24',NULL),
	(78,'中国男足取得世界杯冠军','<p>这是不可能的事情</p>','赵岳宁','2018-01-24',NULL),
	(79,'abc','init setting','abc','2018-01-24',NULL),
	(80,'abc','init setting','abc','2018-01-24',NULL),
	(81,'abc','init setting','abc','2018-01-24',NULL),
	(82,'abc','init setting','abc','2018-01-24',NULL),
	(83,'abc','init setting','abc','2018-01-24',NULL),
	(84,'fsxlvjljfl','<p>sdjfkljlkdfjlsjdflinit contentf</p>','zhao','2018-01-29',NULL),
	(85,'fjsdklfjklsd','<p>sdfsdfdsfsdfinit contentf</p>','jdsklfjlkdsj','2018-02-02',NULL),
	(88,'zhao','<p><img src=\"http://p4fkxpg80.bkt.clouddn.com/15194014281393d3bbfda-e928-4ca7-a561-bcb7254e63b0\" alt=\"\" width=\"216\" height=\"216\" />init setting</p>','zhao','2018-02-23',1),
	(89,'1','init setting','1','2018-02-24',1),
	(90,'1','init setting','1','2018-02-24',1),
	(91,'1','init setting','1','2018-02-24',1),
	(92,'1','init setting','1','2018-02-24',1),
	(93,'1','init setting','1','2018-02-24',1),
	(94,'1','init setting','1','2018-02-24',1),
	(95,'1','init setting','1','2018-02-24',1),
	(96,'1','init setting','1','2018-02-24',1),
	(97,'34234','init setting','2342342','2018-02-24',NULL);

/*!40000 ALTER TABLE `t_news` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_news_module
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_news_module`;

CREATE TABLE `t_news_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `number` bigint(20) NOT NULL DEFAULT '0' COMMENT '文章数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_news_module` WRITE;
/*!40000 ALTER TABLE `t_news_module` DISABLE KEYS */;

INSERT INTO `t_news_module` (`id`, `name`, `remark`, `number`)
VALUES
	(1,'test','test ',4),
	(2,'2222','2',0);

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
  `group_id` int(11) unsigned DEFAULT NULL COMMENT '资源组 便于后台管理',
  `remark` varchar(100) NOT NULL DEFAULT 'null' COMMENT '资源的备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `method` (`method`,`url`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `t_resource_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `t_resource_group` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_resource` WRITE;
/*!40000 ALTER TABLE `t_resource` DISABLE KEYS */;

INSERT INTO `t_resource` (`id`, `name`, `method`, `url`, `status`, `group_id`, `remark`)
VALUES
	(1,'查看文章',0,'/hnister-news-service/news/@/for=page',0,6,'null'),
	(2,'login',2,'/hnister-security-service/user/admin/login',0,NULL,'null'),
	(3,'user info',0,'/hnister-security-service/user/admin/info',0,NULL,'null'),
	(4,'user logout',2,'/hnister-security-service/user/admin/logout',0,NULL,'null'),
	(5,'查看文章模块',0,'/hnister-news-service/newsModules',1,6,''),
	(6,'文件上传Token获取',0,'/hnister-file-service/upToken',0,NULL,'null');

/*!40000 ALTER TABLE `t_resource` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_resource_group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_resource_group`;

CREATE TABLE `t_resource_group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_resource_group` WRITE;
/*!40000 ALTER TABLE `t_resource_group` DISABLE KEYS */;

INSERT INTO `t_resource_group` (`id`, `name`, `remark`)
VALUES
	(6,'news','文章服务资源组'),
	(7,'other','其他'),
	(8,'security','安全认证组');

/*!40000 ALTER TABLE `t_resource_group` ENABLE KEYS */;
UNLOCK TABLES;


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
	(2,'general','普通用户'),
	(3,'普通管理员','普通管理员');

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
  CONSTRAINT `t_role_resource_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `t_role_resource_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_role_resource` WRITE;
/*!40000 ALTER TABLE `t_role_resource` DISABLE KEYS */;

INSERT INTO `t_role_resource` (`role_id`, `resource_id`)
VALUES
	(1,1),
	(3,1),
	(1,2),
	(2,2),
	(1,3),
	(2,3),
	(3,5);

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
  CONSTRAINT `t_role_user_ibfk_1` FOREIGN KEY (`username`) REFERENCES `t_user` (`username`) ON DELETE CASCADE,
  CONSTRAINT `t_role_user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_role_user` WRITE;
/*!40000 ALTER TABLE `t_role_user` DISABLE KEYS */;

INSERT INTO `t_role_user` (`role_id`, `username`)
VALUES
	(3,'610786189'),
	(1,'buynow'),
	(1,'testadmin3'),
	(1,'testuser2'),
	(2,'testuser2');

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

LOCK TABLES `t_specialty` WRITE;
/*!40000 ALTER TABLE `t_specialty` DISABLE KEYS */;

INSERT INTO `t_specialty` (`id`, `college_id`, `name`)
VALUES
	(1,1,'计算机科学与技术');

/*!40000 ALTER TABLE `t_specialty` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `zlass_id` int(11) unsigned DEFAULT NULL,
  `name` varchar(40) NOT NULL DEFAULT '',
  `birth` date DEFAULT NULL,
  `gender` int(2) NOT NULL DEFAULT '1' COMMENT '1 man 0 woman',
  `status` smallint(2) unsigned NOT NULL DEFAULT '0' COMMENT '校友账号状态 0：未验证 1：已验证',
  `city` varchar(11) DEFAULT NULL COMMENT '当前所在地 长沙',
  `salt` varchar(100) NOT NULL DEFAULT '' COMMENT '密码加密盐',
  `password` varchar(100) NOT NULL DEFAULT '',
  `username` varchar(50) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '账号创建时间',
  `avatar` varchar(150) DEFAULT NULL COMMENT '用户头像',
  `type` smallint(2) unsigned NOT NULL DEFAULT '0' COMMENT '用户类型 0 普通用户 1 管理员用户 管理员用户可登录后台管理系统',
  PRIMARY KEY (`username`),
  KEY `zlass_id` (`zlass_id`),
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`zlass_id`) REFERENCES `t_zlass` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;

INSERT INTO `t_user` (`zlass_id`, `name`, `birth`, `gender`, `status`, `city`, `salt`, `password`, `username`, `create_time`, `avatar`, `type`)
VALUES
	(1,'zhao','1996-10-04',1,1,'长沙','bc76b19c-e927-44f1-a168-8d365872dc4b','a3150713cc2e88c28b7a75d48104939ce941ca6d172c9e904b66958e6430f4ec','610786189','2018-02-17 11:16:05','http://p4fkxpg80.bkt.clouddn.com/a834d2f4-1e4b-4726-9e29-017cfd9bcdd1',1),
	(NULL,'zhao',NULL,1,1,NULL,'1517134883545','0f92f2c1e04431ae8f22992c86648d98c19c3d90ef79e70edefa1c96cf99722c','buynow','2018-02-10 10:31:28','http://p4fkxpg80.bkt.clouddn.com/66994c4a-fc0a-4c66-8074-bf271217b331',1),
	(2,'test','1996-10-04',1,0,'长沙','6547bafd-acec-4601-b0a3-07f1e31dc4af','5ecfd90701cc483f92cab24d20e99be6c2e0ba52e71bc8bc5f24da000301e6e8','testadmin','2018-02-12 23:27:38',NULL,1),
	(1,'test2','1996-10-11',1,0,'长沙','69bb7c9b-be40-45d5-9bd9-93a5c3585e89','78885b5f2513fd62617c1ee74dd197beb378ac40f0682b5ca23dd4fece06f8cc','testadmin3','2018-02-12 23:39:27',NULL,1),
	(NULL,'test','2018-02-11',1,1,'sz','jfklsdjkfjsdlk','123456','testuser','2018-02-11 16:06:32',NULL,1),
	(NULL,'test','2018-02-11',1,1,'sz','b2599ebf-0373-4e47-845b-d6d0f6f7c5b7','b78398a05b41756d2f29738efdba6a95f322e7881918f035db88adbed690a0fc','testuser2','2018-02-11 16:44:52',NULL,1),
	(NULL,'zhao',NULL,1,0,'changsha','1516953677817','ac439b2a1b74e728f6a7ff34be3329d6a78a4d014489d4d1b798213233cb75ec','zhaoyuening','2018-01-26 16:01:36',NULL,0),
	(NULL,'zhao',NULL,0,0,NULL,'1516960175517','21e6056bcbd02e407b1e71d38fd8de52e50c697854dcdfa56cae8edd8320d498','zhaozhaozhao','2018-02-09 23:11:19',NULL,0),
	(NULL,'zhao',NULL,0,0,NULL,'1516962010803','438de76e78b8cc4dffbbedcd85ee8c64cefe80b4294e518cada9d1cdf1cae9d7','zhaozhaozhao2','2018-02-09 23:11:20',NULL,0);

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

LOCK TABLES `t_zlass` WRITE;
/*!40000 ALTER TABLE `t_zlass` DISABLE KEYS */;

INSERT INTO `t_zlass` (`id`, `name`, `specialty_id`, `grade`)
VALUES
	(1,'N计科14-1F',1,'2014'),
	(2,'N机械14-3F',1,'2014');

/*!40000 ALTER TABLE `t_zlass` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
