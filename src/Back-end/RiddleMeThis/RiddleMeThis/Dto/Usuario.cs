using System.ComponentModel.DataAnnotations;
namespace RiddleMeThis.Dto
{
    public class UsuarioDto
    {
        [Required]
        public int id { get; set; }

        [Required]
        [MaxLength(100)]
        public string? nome { get; set; }

        [Required]
        public int quizzes_feitos { get; set; }

    }
}
