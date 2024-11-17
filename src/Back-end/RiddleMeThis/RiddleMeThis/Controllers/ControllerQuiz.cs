using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using RiddleMeThis.Models;
using MySqlConnector;

namespace RiddleMeThis.Controllers
{
    [Route("api/[Controller]")]
    [ApiController]
    public class ControllerQuiz : ControllerBase
    {

        private static HashSet<int> quizzesExibidos = new HashSet<int>(); // Armazena IDs dos quizzes exibidos
        private static int totalQuizzes; // Total de quizzes disponíveis


        private readonly string? _connectionString;
        public ControllerQuiz(IConfiguration configuration)
        {
            _connectionString = configuration.GetConnectionString("DefaultConnection");
        }


        [HttpGet("ObterQuizAleatorio")]
        public async Task<IActionResult> ObterQuizAleatorio(string dificuldade)
        {
            if (string.IsNullOrWhiteSpace(dificuldade) || !new[] { "1", "2", "3", "4", "5", "6" }.Contains(dificuldade))
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

        [HttpPost("AtualizarQuizzesFeitos")]
        public async Task<IActionResult> AtualizarQuizzesFeitos([FromBody] Usuario usuario)
        {
            if (usuario == null || usuario.id <= 0)
            {
                return BadRequest("Usuário inválido.");
            }

            using (var conn = new MySqlConnection(_connectionString))
            {
                await conn.OpenAsync();

                // Verifica se o usuário existe
                using (var command = conn.CreateCommand())
                {
                    command.CommandText = "SELECT COUNT(*) FROM usuario WHERE id = @id;";
                    command.Parameters.AddWithValue("@id", usuario.id);

                    var exists = Convert.ToInt32(await command.ExecuteScalarAsync());
                    if (exists == 0)
                    {
                        return NotFound("Usuário não encontrado.");
                    }
                }

                // Atualiza a contagem de quizzes feitos
                using (var command = conn.CreateCommand())
                {
                    command.CommandText = "UPDATE usuario SET quizzes_feitos = quizzes_feitos + 1 WHERE id = @id;";
                    command.Parameters.AddWithValue("@id", usuario.id);
                    await command.ExecuteNonQueryAsync();
                }
            }

            return Ok("Contagem de quizzes atualizada com sucesso.");
        }

        [HttpGet("ObterContagemQuizzesFeitos")]
        public async Task<IActionResult> ObterContagemQuizzesFeitos(int usuarioId)
        {
            using (var conn = new MySqlConnection(_connectionString))
            {
                await conn.OpenAsync();

                using (var command = conn.CreateCommand())
                {
                    command.CommandText = "SELECT quizzes_feitos FROM usuario WHERE id = @id;";
                    command.Parameters.AddWithValue("@id", usuarioId);

                    var result = await command.ExecuteScalarAsync();
                    return Ok(result ?? 0); // Retorna 0 se o usuário não existir
                }
            }
        }




    }//funcaoprincipal
}//namespace