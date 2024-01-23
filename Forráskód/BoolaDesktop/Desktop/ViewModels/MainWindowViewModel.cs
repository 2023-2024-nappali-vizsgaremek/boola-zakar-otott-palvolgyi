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
        private NewExpenseViewModel newExpenseViewModel;
     
    public static MainWindowViewModel Instance { get;  set; }

        public MainWindowViewModel(NewExpenseViewModel newExpenseViewModel)
        {
            ChildViewModel = new MainMenuViewModel();
            if (Instance == null)
            {
                Instance = this;
            }
            this.newExpenseViewModel = newExpenseViewModel;
            
        }
        public MainWindowViewModel()
        {
            ChildViewModel = new MainMenuViewModel();
            if (Instance == null)
            {
                Instance = this;
            }
            this.newExpenseViewModel = new NewExpenseViewModel(null);

        }

        [RelayCommand]
        public void ChangeToAddWindow()
        {
            ChildViewModel =newExpenseViewModel;
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
