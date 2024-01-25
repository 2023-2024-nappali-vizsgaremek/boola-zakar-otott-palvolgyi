using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Models
{
    public class Category
    {
        public int id { get; set; }
        public string name { get; set; }

        public Category()
        {
            id = 0;
            name = "Default";
        }

        public Category(string name, int id)
        {
            this.id = id;
            this.name = name;
        }
        public override string ToString() {
        return id + " " + name;
        
        }
    }
}
