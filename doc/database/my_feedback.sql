DROP DATABASE IF EXISTS `my-feedback`;
CREATE DATABASE `my-feedback` DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE `my-feedback`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `fb_shop`
-- ----------------------------

DROP TABLE IF EXISTS `fb_shop`;
CREATE TABLE `fb_shop` (
  `fb_shop_id` int(10) NOT NULL AUTO_INCREMENT,
  `fb_shop_name` varchar(30) NOT NULL,
  `fb_shop_phone` varchar(30) NOT NULL,
  `fb_shop_address` varchar(200) NOT NULL,
  `fb_shop_email` varchar(30) NOT NULL,
  `fb_shop_password` varchar(30) NOT NULL,
  `fb_shop_token` varchar(30) DEFAULT NULL,
  `fb_expired_at` timestamp NULL DEFAULT NULL,
  `fb_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fb_updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`fb_shop_id`),
  UNIQUE KEY `fb_shop_email` (`fb_shop_email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TRIGGER IF EXISTS `fb_shop_insert_trigger`;
DELIMITER ;;
CREATE TRIGGER `fb_shop_insert_trigger` BEFORE INSERT ON `fb_shop` FOR EACH ROW BEGIN
 SET NEW.fb_updated_at = NOW();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `fb_shop_update_trigger`;
DELIMITER ;;
CREATE TRIGGER `fb_shop_update_trigger` BEFORE UPDATE ON `fb_shop` FOR EACH ROW BEGIN
 SET NEW.fb_updated_at = NOW();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for `fb_shop_task`
-- ----------------------------

DROP TABLE IF EXISTS `fb_shop_task`;
CREATE TABLE `fb_shop_task` (
  `fb_shop_task_id` int(10) NOT NULL AUTO_INCREMENT,
  `fb_shop_id` int(10) NOT NULL,
  `fb_shop_task_email` varchar(30) NOT NULL,
  `fb_shop_task_status` tinyint(4) NOT NULL COMMENT '0:stop,1:start',
  `fb_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fb_updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`fb_shop_task_id`),
  UNIQUE KEY `fb_shop_id_task_email` (`fb_shop_id`,`fb_shop_task_email`),
  KEY `fb_shop_task_fk01` (`fb_shop_id`),
  CONSTRAINT `fb_shop_task_fk01` FOREIGN KEY (`fb_shop_id`) REFERENCES `fb_shop` (`fb_shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TRIGGER IF EXISTS `fb_shop_task_insert_trigger`;
DELIMITER ;;
CREATE TRIGGER `fb_shop_task_insert_trigger` BEFORE INSERT ON `fb_shop_task` FOR EACH ROW BEGIN
 SET NEW.fb_updated_at = NOW();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `fb_shop_task_update_trigger`;
DELIMITER ;;
CREATE TRIGGER `fb_shop_task_update_trigger` BEFORE UPDATE ON `fb_shop_task` FOR EACH ROW BEGIN
 SET NEW.fb_updated_at = NOW();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for `fb_user`
-- ----------------------------

DROP TABLE IF EXISTS `fb_user`;
CREATE TABLE `fb_user` (
  `fb_user_id` int(10) NOT NULL AUTO_INCREMENT,
  `fb_shop_id` int(10) DEFAULT NULL,
  `fb_user_name` varchar(30) NOT NULL,
  `fb_user_phone` varchar(30) NOT NULL,
  `fb_user_password` varchar(30) NOT NULL,
  `fb_user_status` tinyint(4) NOT NULL COMMENT '0:free,1:apply,2:accept',
  `fb_user_token` varchar(30) DEFAULT NULL,
  `fb_expired_at` timestamp NULL DEFAULT NULL,
  `fb_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fb_updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`fb_user_id`),
  UNIQUE KEY `fb_user_phone` (`fb_user_phone`),
  UNIQUE KEY `fb_user_token` (`fb_user_token`),
  KEY `fb_user_fk01` (`fb_shop_id`),
  CONSTRAINT `fb_user_fk01` FOREIGN KEY (`fb_shop_id`) REFERENCES `fb_shop` (`fb_shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TRIGGER IF EXISTS `fb_user_insert_trigger`;
DELIMITER ;;
CREATE TRIGGER `fb_user_insert_trigger` BEFORE INSERT ON `fb_user` FOR EACH ROW BEGIN
 SET NEW.fb_updated_at = NOW();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `fb_user_update_trigger`;
DELIMITER ;;
CREATE TRIGGER `fb_user_update_trigger` BEFORE UPDATE ON `fb_user` FOR EACH ROW BEGIN
 SET NEW.fb_updated_at = NOW();
END
;;
DELIMITER ;

-- ----------------------------
-- Table structure for `fb_feedback`
-- ----------------------------

DROP TABLE IF EXISTS `fb_feedback`;
CREATE TABLE `fb_feedback` (
  `fb_feedback_id` int(10) NOT NULL AUTO_INCREMENT,
  `fb_user_id` int(10) NOT NULL,
  `fb_shop_id` int(10) NOT NULL,
  `fb_feedback_phone` varchar(30) NOT NULL,
  `fb_feedback_content` varchar(200) DEFAULT NULL,
  `fb_feedback_type` tinyint(4) NOT NULL COMMENT '0:good,1:normal,2:bad',
  `fb_feedback_status` tinyint(4) NOT NULL COMMENT '0:new,1:deal',
  `fb_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fb_updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`fb_feedback_id`),
  KEY `fb_feedback_fk01` (`fb_user_id`),
  KEY `fb_feedback_fk02` (`fb_shop_id`),
  CONSTRAINT `fb_feedback_fk01` FOREIGN KEY (`fb_user_id`) REFERENCES `fb_user` (`fb_user_id`),
  CONSTRAINT `fb_feedback_fk02` FOREIGN KEY (`fb_shop_id`) REFERENCES `fb_shop` (`fb_shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TRIGGER IF EXISTS `fb_feedback_insert_trigger`;
DELIMITER ;;
CREATE TRIGGER `fb_feedback_insert_trigger` BEFORE INSERT ON `fb_feedback` FOR EACH ROW BEGIN
 SET NEW.fb_updated_at = NOW();
END
;;
DELIMITER ;

DROP TRIGGER IF EXISTS `fb_feedback_update_trigger`;
DELIMITER ;;
CREATE TRIGGER `fb_feedback_update_trigger` BEFORE UPDATE ON `fb_feedback` FOR EACH ROW BEGIN
 SET NEW.fb_updated_at = NOW();
END
;;
DELIMITER ;

SET FOREIGN_KEY_CHECKS=1;
