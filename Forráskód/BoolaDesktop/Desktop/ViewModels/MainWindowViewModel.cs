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
        public static new MainWindowViewModel Instance { get; private set;}

        [ObservableProperty] public new ObservableObject childViewModel;
        private MainMenuViewModel mainMenuViewModel;

        public MainWindowViewModel(NewExpenseViewModel newExpenseViewModel,
            ProfileViewModel profileViewModel, SettingsViewModel settingsViewModel) : base(newExpenseViewModel, profileViewModel,settingsViewModel)
        {
            Instance ??= this;
            this.mainMenuViewModel = new MainMenuViewModel();
            ChildViewModel = childViewModel;
        }

        public MainWindowViewModel() : base(null, null, null)
        {
            Instance ??= this;
            mainMenuViewModel = new MainMenuViewModel();
            childViewModel = mainMenuViewModel;
        }

        [RelayCommand]
        public new void ChangeToAddWindow()
        {
            base.ChangeToAddWindow();
            ChildViewModel = base.childViewModel;
        }

        [RelayCommand]
        public new void ChangeToSettingsWindow()
        {
            base.ChangeToSettingsWindow();
            ChildViewModel = base.childViewModel;
        }

        [RelayCommand]
        public new void ChangeToProfilesWindow()
        {
            base.ChangeToProfilesWindow();
            ChildViewModel = base.childViewModel;
        }

        [RelayCommand]
        public override void ChangeToMainWindow()
        {
            ChildViewModel = mainMenuViewModel;
        }
    }
}