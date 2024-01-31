using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Models
{
    public class Account
    {
        public string email { get; set; }
        public string pwHash { get; set; }
        public string name { get; set; }

        public Account(string email, string pwHash, string name)
        {
            this.email = email;
            this.pwHash = pwHash;
            this.name = name;
        }
    }
}
