-- DROP TABLE world_cities;
CREATE TABLE world_cities AS SELECT * FROM CSVREAD('classpath:cities.csv');