using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Models
{
    public class Categories
    {
        public string name { get; set; }

        public Categories() 
        {
            name = "General";
        }

        public Categories(string name)
        {
            this.name = name;
        }
    }
}
