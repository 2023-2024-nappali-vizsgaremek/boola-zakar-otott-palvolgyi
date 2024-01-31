using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Models
{
   public  class Login
    {
        public string email { get; set; }
        public string Password { get; set; }
        public Login(string email, string password)
        {
            this.email = email;
            Password = password;
        }
        public Login() {
            email = "valami@gmail.com";
            Password = "***********";
        }
    }
}
