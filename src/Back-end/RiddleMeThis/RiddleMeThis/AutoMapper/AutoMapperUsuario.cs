using RiddleMeThis.Dto;
using RiddleMeThis.Models;

namespace AutoMapper
{
    public class AutoMapperUsuario : Profile
    {
        public AutoMapperUsuario()
        {
            CreateMap<Usuario, UsuarioDto>()
                .ReverseMap();
        }
    }
}
