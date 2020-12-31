USE `sales`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       int         NOT NULL,
    `username` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    `enabled`  tinyint(1)  NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `user`
VALUES (1, 'jagdeep', '{noop}test123', 1),
       (2, 'pradeep', '{noop}test123', 1),
       (3, 'sandeep', '{noop}test123', 1),
       (4, 'gargi',   '{noop}test123', 1),
       (5, 'santosh', '{noop}test123', 1);


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities`
(
    `id`        int         NOT NULL,
    `authority` varchar(50) NOT NULL,
    UNIQUE KEY `authorities_idx_1` (`id`,`authority`),
    CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities`
VALUES (1, 'ROLE_EMPLOYEE'),
       (2, 'ROLE_EMPLOYEE'),
       (3, 'ROLE_MANAGER'),
       (4, 'ROLE_EMPLOYEE'),
       (5, 'ROLE_ADMIN');

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`
(
    `authorities_id` int NOT NULL,
    `user_id`        int NOT NULL,
    CONSTRAINT `authority_ibfk_1` FOREIGN KEY (`authorities_id`) REFERENCES `authorities` (`id`),
    CONSTRAINT `authority_2bfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authority`
--

INSERT INTO `authority`(`authorities_id`, `user_id`)
VALUES (1, 1);

