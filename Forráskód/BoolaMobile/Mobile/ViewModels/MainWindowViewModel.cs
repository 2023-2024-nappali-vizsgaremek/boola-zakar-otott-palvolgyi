using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Desktop.Models;
using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using BoolaShared.ViewModels;

namespace Desktop.ViewModels
{
    public partial class MainWindowViewModel : BoolaShared.ViewModels.MainWindowViewModel
    {
        protected override ObservableObject OpenedChildViewModel { get => ChildViewModel; set => ChildViewModel = value; }
        [ObservableProperty]
        private ObservableObject childViewModel;
        private ObservableObject mainMenuViewModel;

        public MainWindowViewModel()
        {
            childViewModel = new MainMenuViewModel();
        }

        public MainWindowViewModel(LoginViewModel childViewModel, MainMenuViewModel mainMenuViewModel,
            NewExpenseViewModel newExpenseViewModel, ProfileViewModel profileViewModel,
            BoolaShared.ViewModels.SettingsViewModel settingsViewModel) : base(newExpenseViewModel, profileViewModel,
                settingsViewModel)
        {
            ChildViewModel = childViewModel;
            this.mainMenuViewModel = mainMenuViewModel;
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

        public override void ChangeToMainWindow()
        {
            ChildViewModel = mainMenuViewModel;
        }
    }
}
