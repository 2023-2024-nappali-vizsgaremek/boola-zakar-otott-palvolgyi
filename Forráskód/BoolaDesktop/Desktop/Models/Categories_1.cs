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
        public string code { get; set; }
        public Categories(string name, string code)
        {
            this.name = name;
            this.code = code;
        }
        public Categories() {
            this.name = "Forint";
            this.code = "HUF";
            
        }
    }
}
