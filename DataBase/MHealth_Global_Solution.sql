drop table pedido cascade constraints;
drop table usuario cascade constraints;

CREATE TABLE pedido (
    id             NUMBER(8) NOT NULL,
    foi_aceito     VARCHAR2(5) NOT NULL,
    nivel_dor      VARCHAR2(30),
    local_dor      VARCHAR2(50),
    tipo_medicacao VARCHAR2(40),
    temperatura    VARCHAR2(30),
    acompanhante   VARCHAR2(5),
    tipo_comida    VARCHAR2(50),
    usuario_id     NUMBER(8) NOT NULL
);

ALTER TABLE pedido ADD CONSTRAINT pedido_pk PRIMARY KEY ( id );

CREATE TABLE usuario (
    id_usuario           NUMBER(8) NOT NULL,
    nome_usuario         VARCHAR2(100) NOT NULL,
    cpf_usuario          VARCHAR2(14) NOT NULL UNIQUE,
    senha_usuario        VARCHAR2(100) NOT NULL,
    idade_usuario        NUMBER(3) NOT NULL,
    email_usuario        VARCHAR2(100) NOT NULL UNIQUE,
    rg_usuario           VARCHAR2(20) NOT NULL UNIQUE,
    tipo_usuario         VARCHAR2(30) NOT NULL,
    descricao_usuario    VARCHAR2(200) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id_usuario );

ALTER TABLE pedido
    ADD CONSTRAINT pedido_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES usuario ( id_usuario );

drop sequence usuario_id_seq;

CREATE SEQUENCE usuario_id_seq start with 1 increment by 1;

CREATE OR REPLACE TRIGGER usuario_insert
BEFORE INSERT ON usuario
FOR EACH ROW
BEGIN 
    SELECT usuario_id_seq.NEXTVAL INTO :NEW.id_usuario FROM dual;
END;

drop sequence pedido_id_seq;

CREATE SEQUENCE pedido_id_seq start with 1 increment by 1;

CREATE OR REPLACE TRIGGER pedido_insert
BEFORE INSERT ON pedido
FOR EACH ROW
BEGIN 
    SELECT pedido_id_seq.NEXTVAL INTO :NEW.id FROM dual;
END;


--------------------------------------------------------------------------------------------------------------------
create or replace procedure insere_dados_usuario (p_senha in varchar2, p_cpf in varchar2, p_tipo_usuario in varchar2, p_nome in varchar2,
 p_rg  in varchar2, p_idade in number, p_email in varchar2, p_descricao in varchar2) is 
    begin 
        insert into Usuario ( senha_usuario, cpf_usuario, tipo_usuario, nome_usuario, rg_usuario, idade_usuario, email_usuario, descricao_usuario ) values
        ( p_senha, p_cpf, p_tipo_usuario, p_nome, p_rg, p_idade, p_email, p_descricao );
        commit;
    end;

create or replace procedure insere_dados_pedido (p_foi_aceito in varchar2, p_nivel_dor in number, p_local_dor in varchar2,
p_tipo_medicacao in varchar2, p_tipo_comida in varchar2, p_temperatura in varchar2, p_acompanhante in varchar2, p_usuario_id in number) is 
    begin 
        insert into Pedido ( foi_aceito, nivel_dor, local_dor, tipo_medicacao, tipo_comida, temperatura, acompanhante, usuario_id) values
        ( p_foi_aceito, p_nivel_dor, p_local_dor, p_tipo_medicacao, p_tipo_comida, p_temperatura, p_acompanhante, p_usuario_id);
        commit;
    end;

begin
    insere_dados_usuario('$2a$10$W5ZFlRrq00.kWlQGe3Wpjucc4uAJFwrvCmej7uOW74sRjLOQYWkCW', '10198945632', 'FUNCIONARIO', 'Roberto Carlos', '222916837', 32, 'robertoCC@gmail.com', 'Secretário do hospital.');
    insere_dados_usuario('$2a$10$VDVVuYrcavh53I7HrE29u.a1WBX28.OTq524L6abv3RJuFMh77gPG', '83853868827', 'PACIENTE', 'Enrico Theo Novaes', '505914992', 19, 'enrico.theo.novaes@gmail.com', 'Paciente sem alergias');
    insere_dados_usuario('$2a$10$K3MR.0kfxY9eNJFHMJqXI.k.kFigbPmXy0CU0a4iYo9E0UcSjlOhW', '75135792457', 'FUNCIONARIO', 'Raul Fábio Theo Brito' ,'104268864', 38, 'raul_fabio_brito@bol.com', 'Enfermeiro');
    insere_dados_usuario('$2a$10$gYm7s3gU0pIYLDX2gp9uUe8iFGOqgPTV6a.sodSz5JeYsZ1Y2xa0u', '57587783970', 'PACIENTE', 'Paulo Thales Melo', '267235562', 32, 'paulaoMeloo@gmail.com.br', 'Paciente sem alergias ou cuidados especiais.');
    insere_dados_usuario('$2a$10$KwaJnuk8pw9Zym88u5.hkujsKaFWK3g63xT61fQy3JQ4d85/LkEU6', '09231963317', 'FUNCIONARIO', 'Benedita Cristiane Julia Jesus', '248364315', 47 , 'benedita-jesus70@bing.com', 'Enfermeira');
end;

begin 
    insere_dados_pedido('true', 9, 'Costas','Oral',null, null, null, 1);
    insere_dados_pedido('true', null, null,null,'Líquida','Fria', 'sim', 1);
    insere_dados_pedido('true', 5, 'Febre','Oral',null, null, null, 5);
    insere_dados_pedido('true', null, null,null,'Líquida', 'Quente', 'não', 4);
    insere_dados_pedido('false', 1, 'Dor de cabeça','Venosa',null, null, null, 3);
end;



select * from pedido;
select * from usuario;
-----------------------------------------------------------------------------------------------------------------

set serveroutput on;

create or replace procedure busca_usuario(p_id_usuario in number) is
    cursor c_usuarios is select * from usuario where id_usuario = p_id_usuario;
    v_usuarios c_usuarios%rowtype;

    begin
        open c_usuarios;
        loop
        fetch c_usuarios into v_usuarios;
        exit when c_usuarios%notfound;
            dbms_output.put_line('Id: '||v_usuarios.id_usuario);
            dbms_output.put_line('Cpf: '||v_usuarios.cpf_usuario);
            dbms_output.put_line('Tipo de Usuário: '||v_usuarios.tipo_usuario);
            dbms_output.put_line('Nome: '||v_usuarios.nome_usuario);
            dbms_output.put_line('RG: '||v_usuarios.rg_usuario);
            dbms_output.put_line('Idade: '||v_usuarios.idade_usuario);
            dbms_output.put_line('Email: '||v_usuarios.email_usuario);
            dbms_output.put_line('Senha: '||v_usuarios.senha_usuario); 
            dbms_output.put_line('Descrição: '||v_usuarios.descricao_usuario);        
        end loop;
        close c_usuarios;
        exception 
        when NO_DATA_FOUND then
            dbms_output.put_line('O paciente não foi encontrado.');
        when INVALID_CURSOR	 then 
            dbms_output.put_line('Cursor Inválido');
        when INVALID_NUMBER then
            dbms_output.put_line('Número Inválido.');
        when TOO_MANY_ROWS then 
            dbms_output.put_line('Tem mais de uma coluna');
        when others then
            dbms_output.put_line('Erro.');	
end;


create or replace procedure busca_pedido(p_id_pedido in number) is
    cursor c_pedido is select * from pedido where id = p_id_pedido;
    v_pedido c_pedido%rowtype;

    begin
        open c_pedido;
        loop
        fetch c_pedido into v_pedido;
        exit when c_pedido%notfound;
            dbms_output.put_line('Id: '||v_pedido.id);
            dbms_output.put_line('Tipo medicação: '||v_pedido.tipo_medicacao);
            dbms_output.put_line('Local dor: '||v_pedido.local_dor);
            dbms_output.put_line('Nível dor: '||v_pedido.nivel_dor);
            dbms_output.put_line('Temperatura da comida: '||v_pedido.temperatura);
            dbms_output.put_line('Tipo ded comida: '||v_pedido.tipo_comida);
            dbms_output.put_line('Possui acompanhantes: '||v_pedido.acompanhante);
            dbms_output.put_line('Foi Aceito: '||v_pedido.foi_aceito);

        end loop;
        close c_pedido;
        exception 
        when NO_DATA_FOUND then
            dbms_output.put_line('O pedido não foi encontrado.');
        when INVALID_CURSOR	 then 
            dbms_output.put_line('Cursor Inválido');
        when INVALID_NUMBER then
            dbms_output.put_line('Número Inválido.');
        when TOO_MANY_ROWS then 
            dbms_output.put_line('Tem mais de uma coluna');
        when others then
            dbms_output.put_line('Erro.');	
end;

begin 
    busca_usuario(1);
    dbms_output.put_line('---------------------------------');
    busca_usuario(2);
    dbms_output.put_line('---------------------------------');
    busca_usuario(3);
    dbms_output.put_line('---------------------------------');
    busca_usuario(4);
    dbms_output.put_line('---------------------------------');
    busca_usuario(5);
    dbms_output.put_line('---------------------------------');
end;

begin 
    busca_pedido(1);
    dbms_output.put_line('---------------------------------');
    busca_pedido(2);
    dbms_output.put_line('---------------------------------');
    busca_pedido(3);
    dbms_output.put_line('---------------------------------');
    busca_pedido(4);
    dbms_output.put_line('---------------------------------');
    busca_pedido(5);
    dbms_output.put_line('---------------------------------');
end;
