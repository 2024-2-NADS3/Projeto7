create database quiz;
use quiz;

Create Table usuario(
	id INT primary key auto_increment,
    nome varchar(100),
    email varchar(100) unique,
    senha varchar(100),
    pontuacao int default 0
);

Create Table quizzes(
	id_quiz int primary key auto_increment,
    titulo varchar(255),
    descricao TEXT,
    dificuldade enum("1", "2", "3") not null,
    pontuacao_quiz int not null,
    imagen text
);


Create Table perguntas(
	id_perguntas int primary key auto_increment,
    id_quiz int,
    pergunta varchar(255),
    foreign key (id_quiz) references quizzes(id_quiz),
    resposta boolean
);








