-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.1.19-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 ykinfo 的数据库结构
CREATE DATABASE IF NOT EXISTS `ykinfo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ykinfo`;


-- 导出  表 ykinfo.comment 结构
CREATE TABLE IF NOT EXISTS `comment` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `info` varchar(32) NOT NULL COMMENT '文章',
  `userid` varchar(32) NOT NULL COMMENT '发表者',
  `content` text COMMENT '内容',
  `ilike` int(11) DEFAULT NULL COMMENT '我喜欢',
  `status` varchar(255) NOT NULL COMMENT '状态',
  `ctime` datetime NOT NULL COMMENT '时间',
  `embed` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user` (`userid`),
  KEY `info` (`info`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

-- 正在导出表  ykinfo.comment 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`id`, `info`, `userid`, `content`, `ilike`, `status`, `ctime`, `embed`) VALUES
	('12821bcc15ac4ce7a0a6bb4c5fc709d8', 'ddd', '0042dd84ff4d4246a0e3d06095392a86', '需要在被循环渲染的元素', NULL, 'normal', '2017-02-21 16:03:06', 'yes'),
	('8b31b188f873493db2182a310cf737c9', '414cc49fb50c410a8a1954809eca1f70', '0042dd84ff4d4246a0e3d06095392a86', 'iiiiiiii', NULL, 'normal', '2017-03-03 08:59:07', 'yes'),
	('aaa', 'ddd', '0042dd84ff4d4246a0e3d06095392a86', 'Thymeleaf同样支持多路选择Switch结构：', NULL, 'normal', '2017-02-21 14:42:51', 'no'),
	('b66df5375f8d4f188c55e316f4233d1d', 'ddd', '0042dd84ff4d4246a0e3d06095392a86', 'yyyyyyy', NULL, 'normal', '2017-02-23 08:54:59', 'yes'),
	('bbb', 'ddd', '0042dd84ff4d4246a0e3d06095392a86', '下面用一段代码来举例一些常用的方法：', NULL, 'normal', '2017-02-21 14:44:01', 'no'),
	('e123b837f46749c786d0593beba0adfc', 'ddd', '0042dd84ff4d4246a0e3d06095392a86', '需要在被循环渲染的元素', NULL, 'normal', '2017-02-21 16:04:03', 'yes');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;


-- 导出  表 ykinfo.info 结构
CREATE TABLE IF NOT EXISTS `info` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `userid` varchar(32) NOT NULL COMMENT '所属账户',
  `communityid` varchar(32) DEFAULT NULL COMMENT '群组',
  `courseid` varchar(32) DEFAULT NULL,
  `lessonid` varchar(255) DEFAULT NULL,
  `eventid` varchar(32) DEFAULT NULL COMMENT '所属活动',
  `taskid` varchar(32) DEFAULT NULL COMMENT '任务',
  `scope` varchar(255) NOT NULL,
  `friend` text,
  `syscateid` varchar(255) DEFAULT NULL,
  `cmcateid` varchar(255) DEFAULT NULL COMMENT '社群中的信息分类',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `image` text,
  `link` text,
  `markdown` longtext,
  `content` longtext COMMENT '详细内容',
  `status` varchar(255) NOT NULL,
  `ilike` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `cmcount` int(11) NOT NULL,
  `sharecount` int(11) NOT NULL,
  `readcount` int(11) NOT NULL,
  `config` text,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `ctime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息表';

-- 正在导出表  ykinfo.info 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `info` DISABLE KEYS */;
INSERT INTO `info` (`id`, `userid`, `communityid`, `courseid`, `lessonid`, `eventid`, `taskid`, `scope`, `friend`, `syscateid`, `cmcateid`, `title`, `image`, `link`, `markdown`, `content`, `status`, `ilike`, `score`, `cmcount`, `sharecount`, `readcount`, `config`, `latitude`, `longitude`, `ctime`) VALUES
	('02437771901147eb8cab9e174cdd78b8', '0042dd84ff4d4246a0e3d06095392a86', NULL, NULL, NULL, NULL, NULL, 'public', NULL, NULL, NULL, NULL, NULL, NULL, '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', 'normal', 0, 0, 0, 0, 0, NULL, NULL, NULL, '2017-03-02 13:12:53'),
	('3ab86b8341a14f848dcfcda9524c4e78', '0042dd84ff4d4246a0e3d06095392a86', NULL, NULL, NULL, NULL, NULL, 'public', NULL, NULL, NULL, NULL, NULL, NULL, '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', 'normal', 0, 0, 0, 0, 0, NULL, NULL, NULL, '2017-03-02 13:11:55'),
	('414cc49fb50c410a8a1954809eca1f70', '0042dd84ff4d4246a0e3d06095392a86', NULL, NULL, NULL, NULL, NULL, 'public', NULL, NULL, NULL, NULL, NULL, NULL, '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', 'normal', 0, 0, 0, 0, 0, NULL, NULL, NULL, '2017-03-02 13:13:57'),
	('50d32948eaa64dbf8bfbde7bd5495786', '0042dd84ff4d4246a0e3d06095392a86', NULL, NULL, NULL, NULL, NULL, 'public', NULL, NULL, NULL, NULL, NULL, NULL, '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', 'normal', 0, 0, 0, 0, 0, NULL, NULL, NULL, '2017-03-02 13:11:55'),
	('63d7ea38265b42e6b5fce03a9115dea7', '0042dd84ff4d4246a0e3d06095392a86', NULL, NULL, NULL, NULL, NULL, 'public', NULL, NULL, NULL, NULL, NULL, NULL, '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', 'normal', 0, 0, 0, 0, 0, NULL, NULL, NULL, '2017-03-02 13:13:57'),
	('8c9f7bee8a8747c7a6ba95926ef2869c', '0042dd84ff4d4246a0e3d06095392a86', NULL, NULL, NULL, NULL, NULL, 'public', NULL, NULL, NULL, NULL, NULL, NULL, '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', 'normal', 0, 0, 0, 0, 0, NULL, NULL, NULL, '2017-03-02 13:12:53'),
	('b0276a2261ab47da9c7cc898dd0e2f29', '0042dd84ff4d4246a0e3d06095392a86', NULL, NULL, NULL, NULL, NULL, 'public', NULL, NULL, NULL, NULL, NULL, NULL, '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', 'normal', 0, 0, 0, 0, 0, NULL, NULL, NULL, '2017-03-02 13:04:58'),
	('ddd', '0042dd84ff4d4246a0e3d06095392a86', NULL, NULL, NULL, NULL, NULL, 'public', NULL, NULL, NULL, 'pppppppppppppppppppp', NULL, NULL, '### 主要特性  - 支持“标准”Markdown / CommonMark和Github风格的语法，也可变身为代码编辑器；	 - 支持实时预览、图片（跨域）上传、预格式文本/代码/表格插入、代码折叠、搜索替换、只读模式、自定义样式主题和多语言语法高亮等功能；', '可以看到，需要在被循环渲染的元素（这里是<tr>）中加入th:each标签，其中th:each="prod : ${prods}"意味着对集合变量prods进行遍历，循环变量是prod在循环体中可以通过表达式访问。', 'home', 0, 0, 0, 0, 25, NULL, NULL, NULL, '2015-08-24 18:11:58');
/*!40000 ALTER TABLE `info` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
