using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using Desktop.Models;
using Desktop.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Windows;

namespace BoolaShared.ViewModels
{
    public abstract class LoginViewModel : ObservableObject
    {
        protected Login login;
        private ILoginService loginService;

        public LoginViewModel(ILoginService loginService)
        {
            this.loginService = loginService;
            login = new Login();
        }
        
        protected async Task Logon()
        {
            var account = await loginService.GetAccount(login);
            var tokens = await loginService.PostLogin(account);
            var stream = new FileStream("account.txt",FileMode.Create);
            using(var sw = new StreamWriter(stream))
            {
                sw.WriteLine("account:");
                JsonSerializer.Serialize(stream,account);
                sw.WriteLine("login: ");
                JsonSerializer.Serialize(stream,login);
                sw.Flush();
                sw.Close();
            }
            if(tokens is null) return;
            AuthService.AuthToken = tokens.access;
            AuthService.RefreshToken = tokens.refresh;
            MainWindowViewModel.Instance.ChangeToMainWindow();
        }
    }
}
