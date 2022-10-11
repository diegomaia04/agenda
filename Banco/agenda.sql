/**
* Agenda de contatos
*@author Diego Maia
*/

-- Comando para verificar os bancos criados
show databases;

-- Comando para criar um novo banco de dados
create database agenda;

-- Comando para selecionar um banco de dados
use agenda; 

-- Comando para excluir um banco de dados
drop database nome_do_banco;

-- Comando usado para criar uma tabela
-- id (tipos de dados: números inteiros)
-- primary key (chave primaria)
-- auto_increment (numeração automatica)
-- varchar(50) (tipos de dados String - 50 caracteres)
-- not null (campo obrigatorio)

create table contatos(
	id int primary key auto_increment, 
    nome varchar(50) not null,
    fone varchar(15) not null,
    email varchar(50)
);

-- verificar tabelas do banco de dados
show tables;

-- descrever a tabela
describe contatos;

