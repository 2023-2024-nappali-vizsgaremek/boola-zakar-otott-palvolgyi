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
        [ObservableProperty] 
     private ObservableObject childViewModel;
     
    public static MainWindowViewModel Instance { get; private set; }

        public MainWindowViewModel()
        {
            ChildViewModel = new MainMenuViewModel();
            if (Instance == null)
            {
                Instance = this;
            }
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
        [RelayCommand]
        public void ChangeToProfilesWindow() {
            ChildViewModel = new ProfileViewModel();
        }
        [RelayCommand]
        public void ChangeToMainWindow()
        {
            ChildViewModel=new MainMenuViewModel();
        }
        [RelayCommand]
        public void ChangeToMoneyWindow() {
            ChildViewModel = new MoneyViewModel();
        }
    }
}
