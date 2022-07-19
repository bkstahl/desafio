insert into Endereco (id, cep, complemento, endereco) values (1, 93320000, 'Apto 202', 'Casemiro de Abreu 55');
insert into Endereco (id, cep, complemento, endereco) values (2, 93320400, '', 'Pedro Adams Filho 782');

insert into Usuario (id, id_endereco, nome, email, senha, nascimento, status) values (1, 1, 'Bruno Stahl', 'bkstahl@gmail.com', '$2a$10$5BtUNmUjbtRaUmoCDWEgvOBWwmI0/8NTR74/Ast2u517ApTu3283G', null, 1);
insert into Usuario (id, id_endereco, nome, email, senha, nascimento, status) values (2, 2, 'Carla Konzen', 'ckonzen@gmail.com', '$2a$10$5BtUNmUjbtRaUmoCDWEgvOBWwmI0/8NTR74/Ast2u517ApTu3283G', null, 1);

--Senha padrao 123 para todos os usuarios