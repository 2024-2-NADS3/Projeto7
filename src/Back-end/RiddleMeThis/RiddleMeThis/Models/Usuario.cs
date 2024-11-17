using System.ComponentModel.DataAnnotations;

namespace RiddleMeThis.Models
{
    public class Usuario
    {
        [Key]
        public int id { get; set; }

        [Required]
        [MaxLength(100)]
        public string? nome { get; set; }
    }
}
