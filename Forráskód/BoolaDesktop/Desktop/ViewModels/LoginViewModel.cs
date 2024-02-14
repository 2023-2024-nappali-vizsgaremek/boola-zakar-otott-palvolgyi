﻿using CommunityToolkit.Mvvm.ComponentModel;
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
<<<<<<< Updated upstream
        private Login login;
        private ILoginService loginService;
        public LoginViewModelDesktop(ILoginService loginService) : base(loginService)
        {
            this.loginService = loginService;
            login = new Login();
        }

        [RelayCommand]
        public async Task Logon()
        {
=======
        [ObservableProperty]
        private Login enteredlogin;
        public LoginViewModelDesktop(ILoginService loginService) : base(loginService)
        {
            enteredlogin = new Login();
        }

        [RelayCommand]
        public async new Task Logon()
        {
            login = Enteredlogin;
>>>>>>> Stashed changes
            await base.Logon();
        }
    }
}
