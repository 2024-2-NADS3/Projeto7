using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using RiddleMeThis.Models;
using MySqlConnector;

namespace RiddleMeThis.Controllers
{
    [Route("api/[Controller]")]
    [ApiController]
    public class ControllerQuiz :ControllerBase
    {
        private readonly string _connectionString;
        private static HashSet<int> quizzesExibidos = new HashSet<int>(); // Armazena IDs dos quizzes exibidos
        private static int totalQuizzes; // Total de quizzes disponíveis

        public ControllerQuiz(IConfiguration configuration)
        {
            _connectionString = configuration.GetConnectionString("DefaultConnection");
        }


        [HttpGet("ObterQuizAleatorio")]
        public async Task<IActionResult> ObterQuizAleatorio(string dificuldade)
        {
            if (string.IsNullOrWhiteSpace(dificuldade) || !new[] { "1", "2", "3" }.Contains(dificuldade))
            {
                return BadRequest("Dificuldade inválida. Use 1, 2 ou 3.");
            }

            using (var conn = new MySqlConnection(_connectionString))
            {
                await conn.OpenAsync();

                // Primeiro, obter o total de quizzes disponíveis se ainda não foi feito
                if (totalQuizzes == 0)
                {
                    using (var command = conn.CreateCommand())
                    {
                        command.CommandText = "SELECT COUNT(*) FROM quizzes WHERE dificuldade = @dificuldade;";
                        command.Parameters.AddWithValue("@dificuldade", dificuldade);
                        totalQuizzes = Convert.ToInt32(await command.ExecuteScalarAsync());
                    }
                }

                // Verifica se todos os quizzes foram exibidos
                if (quizzesExibidos.Count >= totalQuizzes)
                {
                    quizzesExibidos.Clear(); // Limpa o conjunto para reiniciar
                }

                // Obter um quiz aleatório que ainda não foi exibido
                Quiz quiz = null;
                while (quiz == null)
                {
                    using (var command = conn.CreateCommand())
                    {
                        command.CommandText = "SELECT id_quiz, titulo, descricao, pontuacao_quiz, imagen FROM quizzes WHERE dificuldade = @dificuldade AND id_quiz NOT IN (@exibidos) ORDER BY RAND() LIMIT 1;";
                        command.Parameters.AddWithValue("@dificuldade", dificuldade);
                        command.Parameters.AddWithValue("@exibidos", string.Join(",", quizzesExibidos)); // Adiciona IDs exibidos na consulta

                        using (var reader = await command.ExecuteReaderAsync())
                        {
                            if (await reader.ReadAsync())
                            {
                                quiz = new Quiz
                                {
                                    id_quiz = reader.GetInt32(0),
                                    titulo = reader.GetString(1),
                                    descricao = reader.GetString(2),
                                    pontuacao_quiz = reader.GetInt32(3),
                                    imagen = reader.GetString(4)
                                };

                                // Adiciona o ID do quiz exibido ao conjunto
                                quizzesExibidos.Add(quiz.id_quiz);
                            }
                            else
                            {
                                // Se não encontrar, significa que todos os quizzes foram exibidos
                                quiz = null; // Força a saída do loop
                            }
                        }
                    }
                }

                // Agora, obter as perguntas para o quiz encontrado
                var perguntas = new List<Perguntas>();
                using (var command = conn.CreateCommand())
                {
                    command.CommandText = "SELECT id_perguntas, pergunta, resposta FROM perguntas WHERE id_quiz = @id_quiz;";
                    command.Parameters.AddWithValue("@id_quiz", quiz.id_quiz);

                    using (var reader = await command.ExecuteReaderAsync())
                    {
                        while (await reader.ReadAsync())
                        {
                            var pergunta = new Perguntas
                            {
                                id_perguntas = reader.GetInt32(0),
                                perguntas = reader.GetString(1),
                                respostas = reader.GetBoolean(2)
                            };
                            perguntas.Add(pergunta);
                        }
                    }
                }

                quiz.Perguntas = perguntas; // Adiciona as perguntas ao quiz

                return Ok(quiz);
            }
        }


    }//funcaoprincipal
}//namespace




