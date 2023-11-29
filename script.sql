create table Pais(
	id bigserial PRIMARY KEY,
	nome varchar(40),
	dtcadastro date default CURRENT_DATE
);

create table estado(
	id bigserial primary key,
	nome varchar(40),
	paisId bigserial references pais(id),
	dtcadastro date default CURRENT_DATE
);

create table cidade(
	id bigserial primary key,
	nome varchar(40),
	estadoId bigserial references estado(id),
	dtcadastro date default CURRENT_DATE
);

create table bairro(
	id bigserial primary key,
	nome varchar(40),
	cidadeId bigserial references cidade(id),
	dtcadastro date default CURRENT_DATE
);

insert into Pais (nome) values ('Brasil'), ('Estados Unidos');

insert into estado (nome, paisId) values 
('São Paulo', 1), ('Rio de Janeiro', 1), ('Texas', 2), ('Califórnia', 2);

insert into cidade (nome, estadoId) values 
('São Paulo', 1), ('Rio de Janeiro', 2), ('Houston', 3), ('Los Angeles', 4);

insert into bairro (nome, cidadeId) values 
('Jardins', 1), ('Copacabana', 2), ('Downtown', 3), ('Hollywood', 4);


create table genero(
	id bigserial primary key,
	nome varchar(20),
	dtcadastro date default CURRENT_DATE
);

insert into genero(nome) values 
('MASCULINO'),('FEMININO'), ('OUTRO');

create table cliente (
	id bigserial primary key,
	nome varchar(60),
	generoId bigserial references genero(id),
	dtNasc Date,
	cpf varchar(20),
	email varchar(80),
	senha varchar(100),
	dtcadastro date default CURRENT_DATE
);

create table tipotelefone(
	id bigserial primary key,
	nome varchar(20),
	dtcadastro date default CURRENT_DATE
);

insert into tipotelefone(nome) values 
('Residencial'),('Celular');

create table telefone (
	id bigserial primary key,
	ddd varchar(2), 
	numero varchar(15), 
	tipoid bigserial references tipotelefone(id), 
	clienteid bigserial references cliente(id),
	dtcadastro date default CURRENT_DATE
);

create table tiporesidencia (
	id bigserial primary key,
	nome varchar(20),
	dtcadastro date default CURRENT_DATE
);

insert into tiporesidencia(nome) values 
('CASA'),('APARTAMENTO');

create table tipologradouro (
	id bigserial primary key,
	nome varchar(20)
);

insert into tipologradouro(nome) values 
('RUA'),('AVENIDA');


create table tipoendereco (
	id bigserial primary key,
	nome varchar(20),
	dtcadastro date
);

insert into tipoendereco(nome) values 
('Entrega'),('Cobranca'),('Residencial');

create table endereco (
	id bigserial primary key,
	tipoResidencialId bigserial references tiporesidencia(id), 
	tipoLogradouroId bigserial references tipologradouro(id), 
	tipoEnderecoId bigserial references tipoendereco(id),
	clienteId bigserial references cliente(id),
	logradouro varchar(60), 
	numero varchar(10), 
	cep varchar(10), 
	observacoes varchar(100), 
	bairroId bigserial references bairro(id),
	dtcadastro date default CURRENT_DATE
);

ALTER TABLE endereco
DROP CONSTRAINT IF EXISTS endereco_clienteid_fkey;

ALTER TABLE endereco
ADD CONSTRAINT endereco_clienteid_fkey
FOREIGN KEY (clienteid)
REFERENCES cliente(id)
ON DELETE CASCADE;

ALTER TABLE telefone 
DROP CONSTRAINT IF EXISTS telefone_clienteid_fkey;

ALTER TABLE telefone
ADD CONSTRAINT telefone_clienteid_fkey
FOREIGN KEY (clienteid)
REFERENCES cliente(id)
ON DELETE CASCADE;


create table bandeira (
	id bigserial primary key,
	nome varchar(20)
);

insert into bandeira(nome) values 
('Mastercard'),('Visa');

CREATE TABLE cartao_credito (
    id BIGSERIAL PRIMARY KEY,
    numero VARCHAR(16),
    nomeImpresso VARCHAR(100),
    codSeguranca VARCHAR(4),
    clienteId BIGINT,
    bandeiraId bigint
);


ALTER TABLE cartao_credito
ADD CONSTRAINT cartao_credito_clienteid_fkey
FOREIGN KEY (clienteid)
REFERENCES cliente(id)
ON DELETE CASCADE;

