CREATE TABLE `casestudy` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `project` int(11) DEFAULT NULL,
  `requestor` int(11) DEFAULT NULL,
  `requested` datetime DEFAULT NULL,
  `accepted` datetime DEFAULT NULL,
  `published` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `casestudystatus` (
  `id` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `casestudystatus` VALUES (1,'Requested'),(2,'In progress'),(3,'Approved'),(4,'Published'),(5,'Rejected'),(6,'Withdrawn');

ALTER TABLE `casestudy` CHANGE COLUMN `id` `id` INT NOT NULL UNIQUE AUTO_INCREMENT FIRST;
ALTER TABLE projectdb.casestudy ADD mainresearcher VARCHAR(100);
ALTER TABLE projectdb.casestudy ADD contactperson VARCHAR(100);