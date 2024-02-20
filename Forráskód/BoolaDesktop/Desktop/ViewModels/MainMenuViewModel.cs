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
    public partial class MainMenuViewModel : BoolaShared.ViewModels.MainMenuViewModel
    {
        [ObservableProperty]
        private MainContent content;

        public MainMenuViewModel()
        {
            content = new MainContent();
        }


        [RelayCommand]
       public  new void ChangeToAddWindow()
        {
            base.ChangeToAddWindow();
        }


        [RelayCommand]
        public new void ChangeToSettingsWindow()
        {
            base.ChangeToMainWindow();
        }

        [RelayCommand]
        public new void ChangeToProfilesWindow()
        {
            base.ChangeToProfilesWindow();
        }

        [RelayCommand]
        public new void ChangeToMainWindow()
        {
            base.ChangeToMainWindow();
        }

    }
}
