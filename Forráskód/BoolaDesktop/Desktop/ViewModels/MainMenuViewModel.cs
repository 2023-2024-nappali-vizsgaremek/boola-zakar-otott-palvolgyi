using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.ViewModels
{
    public partial class MainMenuViewModel : ObservableObject
    {
        [ObservableProperty] private MainContent content;

        public MainMenuViewModel()
        {
            content = new MainContent();
        }

        [RelayCommand]
        public void ChangeToAddWindow()
        {
            MainWindowViewModel.Instance.ChangeToAddWindow();
        }

        [RelayCommand]
        public void ChangeToSettingsWindow()
        {
           MainWindowViewModel.Instance.ChangeToSettingsWindow();
        }
        [RelayCommand]
        public void ChangeToProfilesWindow() 
        {
        MainWindowViewModel.Instance.ChangeToProfilesWindow();
        }
        [RelayCommand]
        public void ChangeToMainWindow()
        {
            MainWindowViewModel.Instance.BackToTheMain();
        }
    }
}
