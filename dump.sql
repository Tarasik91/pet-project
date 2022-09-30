create database IF NOT EXISTS pet_db;

use pet_db;

CREATE TABLE IF NOT EXISTS `tbl_users` (
    `id` int(11) NOT NULL auto_increment,
    `first_name` varchar(250),
    `last_name` varchar(250),
    `username` varchar(250),
    PRIMARY KEY  (`id`)
    );

INSERT INTO tbl_users (id, first_name, last_name, username) VALUES(1,'First', 'Last', 'user1');