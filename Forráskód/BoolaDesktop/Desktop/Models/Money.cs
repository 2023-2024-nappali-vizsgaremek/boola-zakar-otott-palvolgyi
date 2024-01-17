using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Models
{
   public class Money
    {
        public string name { get; set; }
        public string code { get; set; }
        public Money(string name, string code)
        {
            this.name = name;
            this.code = code;
        }
        public Money() {
            this.name = "Forint";
            this.code = "HUF";
            
        }
    }
}
