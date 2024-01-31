using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using Desktop.Models;
using Desktop.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace Desktop.ViewModels
{
    public partial class LoginViewModel : ObservableObject
    {
        [ObservableProperty]
        private Login login;
        private ILoginService loginService;
        public LoginViewModel(ILoginService loginService)
        {
            this.loginService = loginService;
            Login = new Login();
        }
        [RelayCommand]
        public async Task Logon()
        {
            var account = await loginService.GetAccount(login);
            var tokens = await loginService.PostLogin(account);
            if(tokens is null) return;
            
        }
    }
}
