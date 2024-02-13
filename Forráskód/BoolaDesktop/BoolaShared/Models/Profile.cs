using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Models
{
    public class Profile
    {
        public Guid Id { get; set; }
        public string Name { get; set; }


        public Profile(Guid id, string Name)
        {
            this.Id = id;
            this.Name = Name;


        }
        public Profile()
        {
            Id = Guid.NewGuid();
            Name = "Standard";

        }
        public override string ToString()
        {
            return "Teszt";
        }

    }
}
