use test;

CREATE TABLE `account` (
  `account_id` int(12) NOT NULL AUTO_INCREMENT,
  `account_type` varchar(50),
  `user_ref_key` int(12) NOT NULL,
  `created_date` date,
  PRIMARY KEY (`account_id`),
  FOREIGN KEY (user_ref_key) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;