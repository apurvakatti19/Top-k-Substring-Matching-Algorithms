CREATE TABLE `author` (
  `name` varchar(100) DEFAULT NULL,
  `paper_key` varchar(200) DEFAULT NULL
);
CREATE TABLE `citation` (
  `paper_cite_key` varchar(200) DEFAULT NULL,
  `paper_cited_key` varchar(200) DEFAULT NULL
);
CREATE TABLE `conference` (
  `conf_key` varchar(100) DEFAULT NULL,
  `name` text,
  `detail` text
);
CREATE TABLE `paper` (
  `title` text,
  `year` int(11) DEFAULT '0',
  `conference` text,
  `paper_key` varchar(200) DEFAULT NULL
);
