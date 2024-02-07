using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Desktop.Models;
using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using System.Windows;
using Desktop.Service;

namespace Desktop.ViewModels
{
    public partial class MainWindowViewModel : AsyncInitializedViewModel
    {
        [ObservableProperty]
        private ObservableObject childViewModel;
        private NewExpenseViewModel newExpenseViewModel;

        public static MainWindowViewModel Instance { get; set; }

        public MainWindowViewModel(NewExpenseViewModel newExpenseViewModel,LoginViewModel loginViewModel)
        {
            MessageBox.Show("dis");
            ChildViewModel = loginViewModel;
            Instance ??= this;
            this.newExpenseViewModel = newExpenseViewModel;
        }
        public MainWindowViewModel()
        {
            MessageBox.Show("dat");
            ChildViewModel = new LoginViewModel(new LoginService(null));
            if (Instance == null)
            {
                Instance = this;
            }
            else
            {
                return;
            }
            this.newExpenseViewModel = new NewExpenseViewModel(new CurrencyService(null));
        }

        [RelayCommand]
        public void ChangeToAddWindow()
        {
            ChildViewModel = newExpenseViewModel;
        }


        [RelayCommand]
        public void ChangeToSettingsWindow()
        {
            ChildViewModel = new SettingsViewModel();
        }
        [RelayCommand]
        public void ChangeToProfilesWindow()
        {
            ChildViewModel = new ProfileViewModel();
        }
        [RelayCommand]
        public void ChangeToMainWindow()
        {
            ChildViewModel = new MainMenuViewModel();
        }

    }
}
