CREATE TABLE wish_list (
  wishID int NOT NULL AUTO_INCREMENT,
  name varchar(45) DEFAULT NULL,
  quantity int DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  price decimal(10,0) DEFAULT NULL,
  link varchar(255) DEFAULT NULL,
  reserved tinyint DEFAULT NULL,
  PRIMARY KEY (wishID)
);