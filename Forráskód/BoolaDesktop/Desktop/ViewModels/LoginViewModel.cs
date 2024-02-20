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
		[ObservableProperty]
        private Login enteredLogin;
        private ILoginService loginService;
        public LoginViewModelDesktop(ILoginService loginService) : base(loginService)
        {
            this.loginService = loginService;
            login = new Login();
        }

        [ICommand]
        public async Task Logon()
        {
            enteredLogin = new Login();
        }

        [ICommand]
        public async new Task Logonk()
        {
            login = enteredLogin;
            await base.Logon();
        }
    }
}
