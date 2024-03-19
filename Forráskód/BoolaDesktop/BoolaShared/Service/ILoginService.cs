using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Desktop.Models;

namespace BoolaShared.Service
{
    public  interface ILoginService
    {
        Task<Account?> GetAccount(Login login);
        public Task<LoginTokens?> PostLogin(Account account);
    }
}
