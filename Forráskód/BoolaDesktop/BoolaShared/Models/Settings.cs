using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BoolaShared.Models
{
    public class Settings
    {
        public string Name { get; set; }
        public string email { get; set; }
        public string password { get; set; }
        public nyelvek nyelv { get; set; }

        public Settings(string name, string email, string password, nyelvek nyelv)
        {

            Name = name;
            this.email = email;
            this.password = password;
            this.nyelv = nyelv;


        }
        public override string ToString()
        {
            return $"{Name} {email} {password} {nyelv}";
        }
        public Settings()
        {
            Name = "Patrik";
            email = "valami@gmail.com";
            password = "**********";
            nyelv = nyelvek.magyar;
        }
    }
    public class nyelveke
    {
        public List<nyelvek> lista = new() { nyelvek.angol, nyelvek.francia, nyelvek.japán, nyelvek.magyar, nyelvek.spanyol };
    }

    public enum nyelvek
    {
        angol,
        magyar,
        spanyol,
        francia,
        japán
    }
}
