using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Managers
{
    public class AuthenticationManager
    {
        public static AuthenticationManager Instance { get; private set; }

        public string AccessTokenJsonString => "";



        public static AuthenticationManager Login(LoginTokens tokens)
        {

        }

        private AuthenticationManager(LoginTokens tokens) { }
    }
}
