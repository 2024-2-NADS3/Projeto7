using RiddleMeThis.Dto;
using RiddleMeThis.Models;
using AutoMapper;

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
