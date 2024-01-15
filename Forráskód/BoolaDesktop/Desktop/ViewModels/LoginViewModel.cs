using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace Desktop.ViewModels
{
    public partial class LoginViewModel:ObservableObject
    {
        [ObservableProperty]
        private Login login;
        public LoginViewModel() {

            Login = new Login();
        }
        [RelayCommand]
        public void Logon()
        {
            MessageBox.Show("Sikeres bejelentkezés");
            MainWindowViewModel.Instance.ChangeToMainWindow();
        }
    }
}
