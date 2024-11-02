using System.ComponentModel.DataAnnotations;
namespace RiddleMeThis.Dto
{
    public class UsuarioDto
    {
        [Required]
        [MaxLength(100)]
        public string nome { get; set; }

    }
}
