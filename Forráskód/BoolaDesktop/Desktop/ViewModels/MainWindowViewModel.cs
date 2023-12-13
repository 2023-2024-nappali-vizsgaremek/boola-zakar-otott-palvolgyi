using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Desktop.Models;
using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;

namespace Desktop.ViewModels
{
    public partial class MainWindowViewModel : ObservableObject
    {
        [ObservableProperty] private ObservableObject childViewModel;

        public MainWindowViewModel()
        {
            ChildViewModel = new MainMenuViewModel();
        }

        [RelayCommand]
        public void ChangeToAddWindow()
        {
            ChildViewModel = new NewExpenseViewModel();
        }

        [RelayCommand]
        public void ChangeToSettingsWindow()
        {
            ChildViewModel = new SettingsViewModel();
        }
    }
}
