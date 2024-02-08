using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Service
{
    public static class AuthService
    {
        public static string AuthToken { get; set; } = "";
        public static string RefreshToken { get; set; } = "";

        public static void Refresh(string newAuthToken)
        {
            AuthToken = newAuthToken;
        }

    }
}
