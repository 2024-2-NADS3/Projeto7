using Microsoft.AspNetCore.Mvc;
using MySqlConnector;
using RiddleMeThis.Models;
using RiddleMeThis.Dto;


namespace RiddleMeThis.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ServidorController : ControllerBase
    {
        private readonly string? _connectionString;

        public ServidorController(IConfiguration configuration)
        {
            // Lê a string de conexão do arquivo de configuração
            _connectionString = configuration.GetConnectionString("DefaultConnection");
        }



        [HttpPost("Cadastrar")]
        public async Task<IActionResult> Cadastrar(Usuario usuario)
        {
            if (string.IsNullOrWhiteSpace(usuario.nome))
            {
                return BadRequest("Nome, senha e email são obrigatórios.");
            }

            var conn = new MySqlConnection(_connectionString);
            {
                await conn.OpenAsync();

                var command = conn.CreateCommand();
                {
                    command.CommandText = "INSERT INTO usuario (nome) " +
                                          "SELECT @nome " +
                                          "WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE nome = @nome);";

                    command.Parameters.AddWithValue("@nome", usuario.nome);


                    int rowCount = await command.ExecuteNonQueryAsync();

                    if (rowCount > 0)
                    {
                        return Ok("Usuário adicionado com sucesso.");
                    }
                    else
                    {
                        return Conflict("Email já está em uso.");
                    }
                }
            }
        }//Postcadastrar

        [HttpPost("Login")]
        public async Task<IActionResult> Login([FromBody] UsuarioDto usuario)
        {
            if (string.IsNullOrWhiteSpace(usuario.nome))
            {
                return BadRequest(new { sucesso = false, mensagem = "O nome do usuário é obrigatório." });
            }

            var conn = new MySqlConnection(_connectionString);
            {
                await conn.OpenAsync();

                var command = conn.CreateCommand();
                {
                    command.CommandText = "SELECT nome FROM usuario WHERE nome = @Nome";
                    command.Parameters.AddWithValue("@Nome", usuario.nome);

                    var reader = await command.ExecuteReaderAsync();
                    {
                        if (await reader.ReadAsync())
                        {
                            return Ok(new { sucesso = true, mensagem = "Login bem-sucedido." });
                        }
                        else
                        {
                            return Unauthorized(new { sucesso = false, mensagem = "Nome de usuário inválido." });
                        }
                    }
                }
            }
        }





        [HttpGet("ObterUsuario")]
        public async Task<IActionResult> ObterUsuario(string email)
        {
            if (string.IsNullOrWhiteSpace(email))
            {
                return BadRequest("Email é obrigatório.");
            }

            var conn = new MySqlConnection(_connectionString);
            {
                await conn.OpenAsync();

                ; var command = conn.CreateCommand();
                {
                    command.CommandText = "SELECT nome FROM usuario WHERE nome = @nome;";
                    command.Parameters.AddWithValue("@email", email);

                    var reader = await command.ExecuteReaderAsync();
                    {
                        if (await reader.ReadAsync())
                        {
                            var usuario = new Usuario
                            {
                                nome = reader.GetString(0),  // Nome
                            };

                            return Ok(usuario);
                        }
                        else
                        {
                            return NotFound("Usuário não encontrado.");
                        }
                    }
                }
            }
        }//ProcurarPeloNome



    }//função principal
}//namespace