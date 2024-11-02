namespace RiddleMeThis.Models
{
    public class Resposta
    {
    }
}

id_perguntas int primary key auto_increment,
id_quiz int,
pergunta varchar(255),
foreign key (id_quiz) references quizzes(id_quiz),
resposta boolean
);
