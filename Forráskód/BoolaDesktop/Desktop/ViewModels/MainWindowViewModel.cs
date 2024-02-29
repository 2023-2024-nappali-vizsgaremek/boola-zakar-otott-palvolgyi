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

        protected override ObservableObject ChildViewModel { get => CurrentChildViewModel;set => CurrentChildViewModel = value; }
        [ObservableProperty] public ObservableObject currentChildViewModel;
        private MainMenuViewModel mainMenuViewModel;

        public MainWindowViewModel(NewExpenseViewModel newExpenseViewModel,
            ProfileViewModel profileViewModel, SettingsViewModel settingsViewModel) : base(newExpenseViewModel, profileViewModel,settingsViewModel)
        {
            Instance ??= this;
            mainMenuViewModel = new MainMenuViewModel();
            CurrentChildViewModel = mainMenuViewModel;
        }

        public MainWindowViewModel() : base(null, null, null)
        {
            Instance ??= this;
            mainMenuViewModel = new MainMenuViewModel();
            currentChildViewModel = mainMenuViewModel;
        }

        [RelayCommand]
        public new void ChangeToAddWindow()
        {
            base.ChangeToAddWindow();
        }

        [RelayCommand]
        public new void ChangeToSettingsWindow()
        {
            base.ChangeToSettingsWindow();
        }

        [RelayCommand]
        public new void ChangeToProfilesWindow()
        {
            base.ChangeToProfilesWindow();
        }

        [RelayCommand]
        public override void ChangeToMainWindow()
        {
            ChildViewModel = mainMenuViewModel;
        }
    }
}