create database encryptor character set utf8mb4 collate utf8mb4_general_ci;

CREATE USER 'encryptor'@'%';
GRANT Alter ON encryptor.* TO 'encryptor'@'%';
GRANT Create ON encryptor.* TO 'encryptor'@'%';
GRANT Create view ON encryptor.* TO 'encryptor'@'%';
GRANT Delete ON encryptor.* TO 'encryptor'@'%';
GRANT Delete history ON encryptor.* TO 'encryptor'@'%';
GRANT Drop ON encryptor.* TO 'encryptor'@'%';
GRANT Grant option ON encryptor.* TO 'encryptor'@'%';
GRANT Index ON encryptor.* TO 'encryptor'@'%';
GRANT Insert ON encryptor.* TO 'encryptor'@'%';
GRANT References ON encryptor.* TO 'encryptor'@'%';
GRANT Select ON encryptor.* TO 'encryptor'@'%';
GRANT Show view ON encryptor.* TO 'encryptor'@'%';
GRANT Trigger ON encryptor.* TO 'encryptor'@'%';
GRANT Update ON encryptor.* TO 'encryptor'@'%';
GRANT Alter routine ON encryptor.* TO 'encryptor'@'%';
GRANT Create routine ON encryptor.* TO 'encryptor'@'%';
GRANT Create temporary tables ON encryptor.* TO 'encryptor'@'%';
GRANT Execute ON encryptor.* TO 'encryptor'@'%';
GRANT Lock tables ON encryptor.* TO 'encryptor'@'%';
FLUSH PRIVILEGES;

CREATE TABLE encryptor.usuarios_tb (
	codigo int NOT NULL AUTO_INCREMENT,
	nome nvarchar(255) NOT NULL,
	senha nvarchar(16) NOT NULL,
	CONSTRAINT usuarios_tb_pk PRIMARY KEY (codigo)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE encryptor.textos_tb (
	codigo int NOT NULL AUTO_INCREMENT,
	texto nvarchar(255) NOT NULL,
	senha nvarchar(16) NOT NULL,
	cod_usuario int NOT NULL,
	CONSTRAINT textos_tb_pk PRIMARY KEY (codigo),
	CONSTRAINT textos_usuario_fk FOREIGN KEY (cod_usuario) REFERENCES encryptor.usuarios_tb(codigo)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;