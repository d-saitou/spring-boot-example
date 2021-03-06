DROP TABLE IF EXISTS `m_user_role`;
CREATE TABLE `m_user_role` (
  `user_id` varchar(10) NOT NULL,
  `role_id` varchar(10) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `created_by` varchar(10) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(10) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
 PRIMARY KEY(
  `user_id`,
  `role_id`
 )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

