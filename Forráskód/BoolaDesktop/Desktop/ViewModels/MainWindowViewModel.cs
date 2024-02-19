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
<<<<<<< Updated upstream
<<<<<<< Updated upstream

        private ObservableObject childViewModel;
        private NewExpenseViewModel newExpenseViewModel;
        private ProfileViewModel profileViewModel;
        private SettingsViewModel settingsViewModel;

        public static MainWindowViewModel Instance { get; set; }


=======
        [ObservableProperty]
        private ObservableObject childViewModel;
        private NewExpenseViewModel newExpenseViewModel;
        private ProfileViewModel profileViewModel;
        private SettingsViewModel settingsViewModel;

        public static MainWindowViewModel Instance { get; set; }


>>>>>>> Stashed changes
=======
        [ObservableProperty]
        private ObservableObject childViewModel;
        private NewExpenseViewModel newExpenseViewModel;
        private ProfileViewModel profileViewModel;
        private SettingsViewModel settingsViewModel;

        public static MainWindowViewModel Instance { get; set; }


>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
<<<<<<< Updated upstream
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
=======
        {
=======
        {
>>>>>>> Stashed changes

            this.childViewModel = childViewModel;
            Instance ??= this;
            this.newExpenseViewModel = newExpenseViewModel;
            this.profileViewModel = profileViewModel;
            this.settingsViewModel = settingsViewModel;
        }

        [RelayCommand]
        public new void ChangeToAddWindow()
        {
            ChildViewModel = newExpenseViewModel;
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
        }



        [RelayCommand]
        public new void ChangeToSettingsWindow()
        {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
            childViewModel = settingsViewModel;
        }

        [RelayCommand]
        public void ChangeToProfilesWindow()
        {
            childViewModel = profileViewModel;
=======
            ChildViewModel = settingsViewModel;
        }

        [RelayCommand]
        public new void ChangeToProfilesWindow()
        {
            ChildViewModel = profileViewModel;
>>>>>>> Stashed changes
        }

        [RelayCommand]
        public override void ChangeToMainWindow()
        {
<<<<<<< Updated upstream
            childViewModel = new MainMenuViewModel();
        }
    }
}
=======
            ChildViewModel = new Mai
>>>>>>> Stashed changes
=======
            ChildViewModel = settingsViewModel;
        }

        [RelayCommand]
        public new void ChangeToProfilesWindow()
        {
            ChildViewModel = profileViewModel;
        }

        [RelayCommand]
        public override void ChangeToMainWindow()
        {
            ChildViewModel = new Mai
>>>>>>> Stashed changes
