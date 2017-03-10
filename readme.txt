база данных test
таблица Person


	






CREATE TABLE `Person` (
			`id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL DEFAULT '',
	`age` int(3) unsigned NOT NULL,
			`admin` BOOLEAN NOT NULL,
			`date` TIMESTAMP NOT NULL,

	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;