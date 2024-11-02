using System.ComponentModel.DataAnnotations;

namespace RiddleMeThis.Models
{
    public class Perguntas
    {
        [Key]
        public int id_perguntas { get; set; }

        [Required]
        [MaxLength(255)]
        public string perguntas { get; set; }

        [Required]
        public Boolean respostas { get; set; }

        public Quiz Quiz { get; set; }
    }
}

