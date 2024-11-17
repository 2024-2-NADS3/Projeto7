using Microsoft.AspNetCore.Http.HttpResults;
using System.ComponentModel.DataAnnotations;

namespace RiddleMeThis.Models
{
    public class Quiz
    {
        [Key]
        public int id_quiz { get; set; }

        [Required]
        [MaxLength(255)]
        public string? titulo { get; set; }

        [Required]
        public string? descricao { get; set; }

        [Required]
        public int dificuldade { get; set; }

        [Required]
        public int pontuacao_quiz { get; set; }

        [Required]
        public string? imagen { get; set; }


        public virtual ICollection<Perguntas>? Perguntas { get; set; }


    }
}
