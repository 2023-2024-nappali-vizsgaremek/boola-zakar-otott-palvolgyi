using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Desktop.Models;

namespace Desktop.Service
{
    public  interface ILoginService
    {
        public Task<Login> postLogin(Account account);
    }
}
