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
    public partial class MainWindowViewModel : BoolaShared.ViewModels.MainWindowViewModel
    {
        [ObservableProperty]
        private ObservableObject childViewModel;
        private NewExpenseViewModel newExpenseViewModel;
        private ProfileViewModel profileViewModel;
        private SettingsViewModel settingsViewModel;

        public static MainWindowViewModel Instance { get; set; }

        public MainWindowViewModel()
        {

            //todo ChildviewModel assigment
            if (Instance == null)
            {
                Instance = this;
            }
            else
            {
                return;
            }

        }

        public MainWindowViewModel(LoginViewModelDesktop childViewModel, NewExpenseViewModel newExpenseViewModel,
            ProfileViewModel profileViewModel, SettingsViewModel settingsViewModel)
        {

            this.childViewModel = childViewModel;
            Instance ??= this;
            this.newExpenseViewModel = newExpenseViewModel;
            this.profileViewModel = profileViewModel;
            this.settingsViewModel = settingsViewModel;
        }

        [RelayCommand]
        public void ChangeToAddWindow()
        {
            childViewModel = newExpenseViewModel;
        }

        [RelayCommand]
        public new void ChangeToSettingsWindow()
        {
            childViewModel = settingsViewModel;
        }

        [RelayCommand]
        public new void ChangeToProfilesWindow()
        {
            ChildViewModel = profileViewModel;
        }

        [RelayCommand]
        public override void ChangeToMainWindow()
        {
            childViewModel = new MainMenuViewModel();
        }
    }
}