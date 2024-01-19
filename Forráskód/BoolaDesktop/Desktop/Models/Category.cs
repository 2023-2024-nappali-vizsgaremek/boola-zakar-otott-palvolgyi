using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Models
{
    public class Category
    {
        public string name { get; set; }

        public Category() 
        {
            name = "General";
        }

        public Category(string name)
        {
            this.name = name;
        }
    }
}
