using Microsoft.EntityFrameworkCore;
using RiddleMeThis.Models;

namespace RiddleMeThis.Data
{
    public class ApiContext : DbContext
    {
        public DbSet<Usuario> usuarios { get; set; }
        public DbSet<Quiz> quizzes { get; set; }
        public DbSet<Perguntas> perguntas { get; set; }

        public ApiContext(DbContextOptions<ApiContext> options) : base(options)
        {
        }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {

            base.OnModelCreating(modelBuilder);
        }
    }
}
