/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.21 : Database - hnister
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hnister` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hnister`;

/*Table structure for table `t_bbs_plate` */

DROP TABLE IF EXISTS `t_bbs_plate`;

CREATE TABLE `t_bbs_plate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '板块名称',
  `remark` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_bbs_plate` */

insert  into `t_bbs_plate`(`id`,`name`,`remark`) values (5,'我的学院','难忘同窗岁月'),(6,'地区板块','在同一片天地下奋斗'),(7,'行业板块','各行的湖理人，携手进步，成为行业翘楚');

/*Table structure for table `t_bbs_reply` */

DROP TABLE IF EXISTS `t_bbs_reply`;

CREATE TABLE `t_bbs_reply` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `tier_id` bigint(20) NOT NULL COMMENT '回复的楼层id',
  `username` varchar(50) NOT NULL COMMENT '发出回复的用户',
  `replyed_username` varchar(50) NOT NULL COMMENT '被回复的用户',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_bbs_reply` */

/*Table structure for table `t_bbs_theme` */

DROP TABLE IF EXISTS `t_bbs_theme`;

CREATE TABLE `t_bbs_theme` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `plate_id` int(11) NOT NULL COMMENT '板块id',
  `remark` varchar(200) NOT NULL COMMENT '主题介绍',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '为后续拓展准备，目前只有类型1',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 正常1',
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_bbs_theme` */

insert  into `t_bbs_theme`(`id`,`plate_id`,`remark`,`type`,`status`,`name`) values (1,5,'南湖校友',1,1,'南湖学院'),(2,5,'计算机学院校友交流',1,1,'计算机学院'),(3,5,'数学学院交流',1,1,'数学学院'),(4,6,'湖理校友在星城',1,1,'长沙校友'),(5,6,'湖理校友在鹏城',1,1,'深圳校友'),(6,7,'湖里互联网人才集中地',1,1,'IT行业交流'),(7,7,'湖理金融大鳄',1,1,'金融行业交流');

/*Table structure for table `t_bbs_tier` */

DROP TABLE IF EXISTS `t_bbs_tier`;

CREATE TABLE `t_bbs_tier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `topic_id` bigint(20) NOT NULL,
  `content` text,
  `number` int(11) NOT NULL COMMENT '层数',
  `username` varchar(50) NOT NULL COMMENT '发表用户',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '1 普通类型',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 1普通',
  `reply_num` int(11) NOT NULL DEFAULT '0' COMMENT '该层回复数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_bbs_tier` */

/*Table structure for table `t_bbs_topic` */

DROP TABLE IF EXISTS `t_bbs_topic`;

CREATE TABLE `t_bbs_topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `plate_id` int(11) NOT NULL DEFAULT '-1' COMMENT '荣誉存储板块id',
  `theme_id` int(11) NOT NULL DEFAULT '-1' COMMENT '主题id',
  `title` varchar(200) NOT NULL,
  `post_time` datetime NOT NULL,
  `username` varchar(50) NOT NULL COMMENT '发表该层的用户',
  `reply` int(11) NOT NULL DEFAULT '0' COMMENT '回复数量',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '类型 1普通',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_bbs_topic` */

/*Table structure for table `t_college` */

DROP TABLE IF EXISTS `t_college`;

CREATE TABLE `t_college` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL DEFAULT '' COMMENT '学院名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_college` */

insert  into `t_college`(`id`,`name`,`remark`) values (1,'南湖学院',NULL);

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL COMMENT '菜单组的ID',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父菜单ID，0为根目录',
  `name` varchar(50) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL COMMENT '访问菜单地址',
  `type` int(11) NOT NULL COMMENT '菜单类型 1、跳网站地址 2、文章模块类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `t_menu` */

insert  into `t_menu`(`id`,`group_id`,`parent_id`,`name`,`remark`,`url`,`type`) values (12,1,0,'校友天地',NULL,'http://www.baidu.com',3),(13,1,12,'杰出校友',NULL,'/index/news/module/1',2),(14,1,12,'杰出校友2',NULL,'',1),(15,1,0,'菜单二',NULL,'',1),(16,1,15,'子菜单',NULL,'',1),(21,1,0,'校友论坛',NULL,'/bbs',2);

/*Table structure for table `t_menu_group` */

DROP TABLE IF EXISTS `t_menu_group`;

CREATE TABLE `t_menu_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(14) NOT NULL,
  `remark` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_menu_group` */

insert  into `t_menu_group`(`id`,`name`,`remark`) values (1,'index nav','首页导航栏');

/*Table structure for table `t_news` */

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
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

/*Data for the table `t_news` */

insert  into `t_news`(`id`,`title`,`content`,`author`,`post_date`,`module_id`) values (15,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(16,'DEMOaaaa','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(24,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(25,'DEMO2345','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(26,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(27,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(28,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(29,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(30,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(31,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(32,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(33,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(34,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(35,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(36,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(37,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(38,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(39,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(40,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(41,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(43,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(44,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(45,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(46,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(47,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(48,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(49,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(50,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(51,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(52,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(53,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(54,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(55,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(56,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(57,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(58,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(59,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(60,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(61,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(62,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(63,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(64,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(65,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(66,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(67,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(68,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(69,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(70,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(71,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(72,'DEMO','<p>init contentDEMO</p>','DEMO','2018-01-10',NULL),(74,'这是一篇测试文章','<article style=\"box-sizing: border-box; box-shadow: rgba(0, 0, 0, 0.05) 0px 2px 4px 0px; background-color: #ffffff; padding: 20px 0px; color: #333333; font-family: \'PingFang SC\', \'Microsoft YaHei\', SimHei, Arial, SimSun; font-size: 16px;\">\n<div id=\"article_content\" class=\"article_content csdn-tracking-statistics tracking-click\" style=\"box-sizing: border-box; margin: 0px 0px 30px; padding: 20px 30px 0px; color: #454545;\" data-mod=\"popu_519\" data-dsm=\"post\">\n<div class=\"markdown_views\" style=\"box-sizing: border-box; margin: 0px; padding: 0px;\">\n<p style=\"box-sizing: border-box; margin: 0px 0px 16px; padding: 0px; color: #4f4f4f; line-height: 26px; text-align: justify;\">从昨天开始使用IDEA开始就一直在搭建java环境，许久没有使用过java，刚开始有些生疏，先建了一个最简单的类：test.java ，可是运行的时候出现 错误：找不到或无法加载主类 。</p>\n<p style=\"box-sizing: border-box; margin: 0px 0px 16px; padding: 0px; color: #4f4f4f; line-height: 26px; text-align: justify;\">在网上找了好久资料，都是环境变量的问题，我在我的window命令行下看了一下java 和javac 两个均是1.7 而且可以正常的编译和运行。排除了java 环境的问题，只能是IDEA环境的问题，在项目的project structure 中，先将项目指定JDK ，然后在项目的PATH 中，将path修改为跟当前项目在一个路径下就可以了&nbsp;<br style=\"box-sizing: border-box;\" /><img style=\"box-sizing: border-box; border: 0px; vertical-align: middle; outline: 0px; margin: 24px 0px; max-width: 100%;\" title=\"\" src=\"http://img.blog.csdn.net/20170607114152084?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzEzODI5MjE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"\" /></p>\n</div>\n</div>\n</article>\n<div class=\"article_copyright\" style=\"box-sizing: border-box; margin: -10px 0px 0px; padding: 10px 20px 30px 30px; box-shadow: rgba(0, 0, 0, 0.05) 0px 2px 4px 0px; font-size: 14px; color: #788087; clear: both; overflow: hidden; background-color: #ffffff; font-family: \'PingFang SC\', \'Microsoft YaHei\', SimHei, Arial, SimSun;\">&nbsp;</div>','赵岳宁','2018-01-23',NULL),(75,'中国男足取得世界杯冠军','<p>这是不可能的事情</p>','赵岳宁','2018-01-24',NULL),(76,'中国男足取得世界杯冠军','<p>这是不可能的事情</p>','赵岳宁','2018-01-24',NULL),(77,'中国男足取得世界杯冠军','<p>这是不可能的事情</p>','赵岳宁','2018-01-24',NULL),(78,'中国男足取得世界杯冠军','<p>这是不可能的事情</p>','赵岳宁','2018-01-24',NULL),(79,'abc','init content','abc','2018-01-24',NULL),(80,'abc','init content','abc','2018-01-24',NULL),(81,'abc','init content','abc','2018-01-24',NULL),(82,'abc','init content','abc','2018-01-24',NULL),(83,'abc','init content','abc','2018-01-24',NULL),(84,'fsxlvjljfl','<p>sdjfkljlkdfjlsjdflinit contentf</p>','zhao','2018-01-29',NULL),(85,'fjsdklfjklsd','<p>sdfsdfdsfsdfinit contentf</p>','jdsklfjlkdsj','2018-02-02',NULL),(86,'1图看库里生涯最牛成就！打出同期乔丹8倍数据','<div id=\"artibody\" class=\"article\" style=\"font-size: 18px; letter-spacing: 1px; line-height: 32px; color: #4d4f53;\" data-sudaclick=\"blk_content\">\n<div class=\"img_wrapper\" style=\"text-align: center;\"><img style=\"border: 0px none; vertical-align: middle; display: block; margin: 0px auto; max-width: 640px;\" src=\"http://n.sinaimg.cn/sports/transform/w650h812/20180315/R20G-fyscsmv9916772.jpg\" alt=\"厉害了！\" /><span class=\"img_descr\" style=\"line-height: 20px; padding: 6px 0px; font-size: 16px; margin: 5px auto; display: inline-block; zoom: 1; text-align: left; font-weight: bold;\">厉害了！</span></div>\n<p style=\"margin: 0px 0px 30px; padding: 0px; text-size-adjust: 100%;\">&nbsp;</p>\n<p style=\"margin: 0px 0px 30px; padding: 0px; text-size-adjust: 100%;\">　　北京时间3月15日消息，今天是美国时间3月14日，斯蒂芬-库里的生日，今天，萌神就真正达到了30周岁的门槛。而他树立了自己在NBA历史上的划时代意义。<span style=\"color: #ff0000;\"><a style=\"text-decoration-line: none; outline: 0px; -webkit-tap-highlight-color: transparent; color: #ff0000;\" href=\"https://sina.17uxi.com/\" target=\"_blank\" rel=\"noopener\">[开局抽到库里！篮球大师开启你的冠军之路]</a></span></p>\n<p style=\"margin: 0px 0px 30px; padding: 0px; text-size-adjust: 100%;\">　　1988年3月14日，斯蒂芬-库里诞生了，当年，没有人想到他日后的成就会比自己的状元秀老爸更大，更没有人想到他这个孩子日后会成为一名超级巨星。</p>\n<p style=\"margin: 0px 0px 30px; padding: 0px; text-size-adjust: 100%;\">　　但现在，库里用自己疯狂的三分投射和逆天的精准度让一切成为了现实。今天年满30周岁的库里，已经被视为NBA有史以来最伟大的射手了，没有之一。</p>\n<p style=\"margin: 0px 0px 30px; padding: 0px; text-size-adjust: 100%;\">　　如美国媒体放出的图片所示，在30岁到来时，库里已经投中了2126个三分球，这一成绩毫无悬念地成为了历史第一，排在他后面的，是詹姆斯-哈登，他投中了1614球；然后是雷-阿伦，君子剑投中了1486球。</p>\n<p style=\"margin: 0px 0px 30px; padding: 0px; text-size-adjust: 100%;\">　　此后的球员，一个比一个大牌&mdash;&mdash;科比-布莱恩特，1086球；雷吉-米勒，1035球；拉里-伯德，267球；迈克尔-乔丹，258球。库里的这个2126球，是乔丹的8.2倍。</p>\n<p style=\"margin: 0px 0px 30px; padding: 0px; text-size-adjust: 100%;\">　　当然，这也与三分球发展的趋势有关，比如克雷-汤普森，今年他只有27岁，但是已经投中了1534个三分球，在这份榜单上也仅次于库里和哈登。<span style=\"color: #ff0000;\"><a style=\"text-decoration-line: none; outline: 0px; -webkit-tap-highlight-color: transparent; color: #ff0000;\" href=\"https://sina.duoniuapp.com/www/#/check?mode=1&amp;pchannelid=13&amp;mchannelid=0&amp;pchannelurl=sina_1&amp;redirecturl=\" target=\"_blank\" rel=\"noopener\">[勇士火箭谁能笑傲西部？竞猜NBA赢每日千元大礼]</a></span></p>\n<p style=\"margin: 0px 0px 30px; padding: 0px; text-size-adjust: 100%;\">　　不过三分球的风潮，也与库里有分不开的关系，正是库里用行动告诉人们，三分球是可以稳定命中的，三分球是可以赢得总冠军的。这或许就是库里对NBA作出的划时代贡献。</p>\n<p style=\"margin: 0px 0px 30px; padding: 0px; text-size-adjust: 100%;\">　　（咕哒）</p>\n<div id=\"left_hzh_ad\">&nbsp;</div>\n</div>\n<div id=\"article-bottom\" class=\"article-bottom clearfix\" style=\"zoom: 1; margin-top: 24px;\">\n<div id=\"keywords\" class=\"keywords\" style=\"float: left; font-size: 16px; font-family: \'Hiragino Sans GB\', \'Microsoft Yahei\', 微软雅黑, SimSun, 宋体, Arial;\" data-wbkey=\"库里,乔丹,科比,体育\" data-sudaclick=\"content_keywords_p\"><label style=\"font-weight: bold; color: #4d4f53;\">关键词 :&nbsp;</label><a style=\"text-decoration-line: none; outline: 0px; -webkit-tap-highlight-color: transparent; color: #4d4f53; padding-right: 10px;\" href=\"http://tags.sports.sina.com.cn/%E5%BA%93%E9%87%8C\" target=\"_blank\" rel=\"noopener\">库里</a><a style=\"text-decoration-line: none; outline: 0px; -webkit-tap-highlight-color: transparent; color: #4d4f53; padding-right: 10px;\" href=\"http://tags.sports.sina.com.cn/%E4%B9%94%E4%B8%B9\" target=\"_blank\" rel=\"noopener\">乔丹</a><a style=\"text-decoration-line: none; outline: 0px; -webkit-tap-highlight-color: transparent; color: #4d4f53; padding-right: 10px;\" href=\"http://tags.sports.sina.com.cn/%E7%A7%91%E6%AF%94\" target=\"_blank\" rel=\"noopener\">科比</a></div>\n<p>&nbsp;</p>\n<div class=\"btns\" style=\"float: right; font-family: \'Hiragino Sans GB\', \'Microsoft Yahei\', 微软雅黑, SimSun, 宋体, Arial; font-size: 12px;\">&nbsp;</div>\n</div>','新浪体育','2018-03-15',1),(87,'测试文章','init content','赵岳宁','2018-04-01',1),(88,'测试文章','init content','赵岳宁','2018-04-01',1),(89,'测试文章','init content','赵岳宁','2018-04-01',1),(90,'测试文章','init content','赵岳宁','2018-04-01',1),(91,'测试文章','init content','赵岳宁','2018-04-01',1),(92,'测试文章','init content','赵岳宁','2018-04-01',1),(93,'测试文章','init content','赵岳宁','2018-04-01',1),(94,'测试文章','init content','赵岳宁','2018-04-01',1),(95,'测试文章','init content','赵岳宁','2018-04-01',1),(96,'测试文章','init content','赵岳宁','2018-04-01',1),(97,'测试文章','init content','赵岳宁','2018-04-01',1),(98,'测试文章','init content','赵岳宁','2018-04-01',1),(99,'测试文章','init content','赵岳宁','2018-04-01',1),(100,'测试文章','init content','赵岳宁','2018-04-01',1),(101,'测试文章','init content','赵岳宁','2018-04-01',1),(102,'测试文章','init content','赵岳宁','2018-04-01',1),(103,'测试文章','init content','赵岳宁','2018-04-01',1),(104,'测试文章','init content','赵岳宁','2018-04-01',1),(105,'测试文章','init content','赵岳宁','2018-04-01',1),(106,'测试文章','init content','赵岳宁','2018-04-01',1),(107,'测试文章','init content','赵岳宁','2018-04-01',1);

/*Table structure for table `t_news_module` */

DROP TABLE IF EXISTS `t_news_module`;

CREATE TABLE `t_news_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `number` bigint(20) DEFAULT '0' COMMENT '文章数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_news_module` */

insert  into `t_news_module`(`id`,`name`,`remark`,`number`) values (1,'体育新闻','体育类新闻',22);

/*Table structure for table `t_resource` */

DROP TABLE IF EXISTS `t_resource`;

CREATE TABLE `t_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `method` int(4) unsigned NOT NULL COMMENT 'GET,\n    HEAD,\n    POST,\n    PUT,\n    PATCH,\n    DELETE,\n    OPTIONS,\n    TRACE;',
  `url` varchar(100) NOT NULL DEFAULT '',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '资源状态 0：白名单不被权限监控 1：被监控 2：废弃',
  `group_id` int(11) unsigned DEFAULT NULL COMMENT '资源组 便于后台管理',
  `remark` varchar(50) NOT NULL DEFAULT 'null' COMMENT '资源备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `method` (`method`,`url`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `t_resource_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `t_resource_group` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_resource` */

insert  into `t_resource`(`id`,`name`,`method`,`url`,`status`,`group_id`,`remark`) values (1,'add news',0,'/hnister-news-service/news',0,6,'null'),(2,'login',2,'/hnister-security-service/user/admin/login',0,NULL,'null'),(3,'user info',0,'/hnister-security-service/user/admin/info',0,NULL,'null'),(4,'user logout',2,'/hnister-security-service/user/admin/logout',0,NULL,'null'),(5,'roles-get',0,'/hnister-security-service/roles',1,7,'null');

/*Table structure for table `t_resource_group` */

DROP TABLE IF EXISTS `t_resource_group`;

CREATE TABLE `t_resource_group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_resource_group` */

insert  into `t_resource_group`(`id`,`name`,`remark`) values (6,'news','文章服务资源组'),(7,'security','安全组');

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL DEFAULT '',
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`name`,`remark`) values (1,'admin','超级管理员权限'),(2,'general','普通用户'),(8,'guest','访客');

/*Table structure for table `t_role_resource` */

DROP TABLE IF EXISTS `t_role_resource`;

CREATE TABLE `t_role_resource` (
  `role_id` int(11) unsigned NOT NULL,
  `resource_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`),
  KEY `resource_id` (`resource_id`),
  CONSTRAINT `t_role_resource_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `t_role_resource_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_resource` */

insert  into `t_role_resource`(`role_id`,`resource_id`) values (1,1),(1,2),(1,5);

/*Table structure for table `t_role_user` */

DROP TABLE IF EXISTS `t_role_user`;

CREATE TABLE `t_role_user` (
  `role_id` int(11) unsigned NOT NULL,
  `username` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_id`,`username`),
  KEY `username` (`username`),
  CONSTRAINT `t_role_user_ibfk_1` FOREIGN KEY (`username`) REFERENCES `t_user` (`username`) ON DELETE CASCADE,
  CONSTRAINT `t_role_user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_user` */

insert  into `t_role_user`(`role_id`,`username`) values (1,'buynow');

/*Table structure for table `t_specialty` */

DROP TABLE IF EXISTS `t_specialty`;

CREATE TABLE `t_specialty` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `college_id` int(11) unsigned NOT NULL,
  `name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_specialty` */

insert  into `t_specialty`(`id`,`college_id`,`name`) values (1,1,'机电系');

/*Table structure for table `t_user` */

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
  `avatar` varchar(150) DEFAULT NULL COMMENT '用户头像',
  `type` smallint(2) unsigned NOT NULL DEFAULT '0' COMMENT '用户类型 0:普通用户 1:管理员用户',
  `specialty_id` int(11) unsigned DEFAULT NULL COMMENT '专业',
  `college_id` int(11) unsigned DEFAULT NULL COMMENT '学院',
  `grad` varchar(40) DEFAULT NULL COMMENT '入学年份',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`zlass_id`,`name`,`birth`,`gender`,`status`,`city`,`salt`,`password`,`username`,`create_time`,`avatar`,`type`,`specialty_id`,`college_id`,`grad`) values (1,'赵岳宁',NULL,1,0,NULL,'cdce90bc-c152-4d78-a1ef-e65dde723f37','2392233d22c01e9bc94f91c57671982b9a53224eb7f71757a1884e6cbd2586d9','','2018-04-12 15:37:08',NULL,0,1,1,'2014'),(NULL,'zhao',NULL,NULL,0,NULL,'1517134883545','0f92f2c1e04431ae8f22992c86648d98c19c3d90ef79e70edefa1c96cf99722c','buynow','2018-02-08 23:03:04','http://localhost:9528/static/images/avatar/default-avatar.jpg',1,NULL,NULL,NULL),(NULL,'zhao',NULL,1,0,'changsha','1516953677817','ac439b2a1b74e728f6a7ff34be3329d6a78a4d014489d4d1b798213233cb75ec','zhaoyuening','2018-01-26 16:01:36',NULL,0,NULL,NULL,NULL),(NULL,'zhao',NULL,NULL,0,NULL,'1516960175517','21e6056bcbd02e407b1e71d38fd8de52e50c697854dcdfa56cae8edd8320d498','zhaozhaozhao','2018-01-26 17:49:40',NULL,0,NULL,NULL,NULL),(NULL,'zhao',NULL,NULL,0,NULL,'1516962010803','438de76e78b8cc4dffbbedcd85ee8c64cefe80b4294e518cada9d1cdf1cae9d7','zhaozhaozhao2','2018-01-26 18:20:10',NULL,0,NULL,NULL,NULL);

/*Table structure for table `t_zlass` */

DROP TABLE IF EXISTS `t_zlass`;

CREATE TABLE `t_zlass` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL DEFAULT '' COMMENT '名称',
  `specialty_id` int(11) unsigned NOT NULL COMMENT '专业',
  `grade` varchar(40) NOT NULL DEFAULT '' COMMENT '年级 如2014级',
  `college_id` int(11) unsigned NOT NULL COMMENT '学院',
  PRIMARY KEY (`id`),
  KEY `specialty_id` (`specialty_id`),
  CONSTRAINT `t_zlass_ibfk_1` FOREIGN KEY (`specialty_id`) REFERENCES `t_specialty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_zlass` */

insert  into `t_zlass`(`id`,`name`,`specialty_id`,`grade`,`college_id`) values (1,'N计科14-1F',1,'2014',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
