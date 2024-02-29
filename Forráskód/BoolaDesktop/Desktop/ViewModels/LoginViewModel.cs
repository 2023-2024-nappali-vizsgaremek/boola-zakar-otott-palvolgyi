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
    public partial class LoginViewModelDesktop : BoolaShared.ViewModels.LoginViewModel
    {
        [ObservableProperty] public bool isVisible = true;
        [ObservableProperty]
        private Login enteredLogin;
        public LoginViewModelDesktop(ILoginService loginService) : base(loginService)
        {
            login = new Login();
            enteredLogin = new Login();
        }

        public LoginViewModelDesktop() : base(null)
        {
            enteredLogin = new Login();
        }

        [RelayCommand]
        public async new Task Logon()
        {
            login = EnteredLogin;
            await base.Logon();
            AuthService.AuthToken = "hi";
            if(AuthService.AuthToken is not "") IsVisible = false;
            else MessageBox.Show("Nem sikerült bejelentkezni!");    //todo: better error messages
        }
    }
}
