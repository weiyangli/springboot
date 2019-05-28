DROP TABLE IF EXISTS `checking`;
CREATE TABLE `checking`  (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(0) DEFAULT NULL,
  `name` varchar(20)  DEFAULT NULL,
  `url` varchar(200)  DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
