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


        [ICommand]
       public  new void ChangeToAddWindow()
        {
            base.ChangeToAddWindow();
        }


        [ICommand]
        public new void ChangeToSettingsWindow()
        {
            base.ChangeToMainWindow();
        }

        [ICommand]
        public new void ChangeToProfilesWindow()
        {
            base.ChangeToProfilesWindow();
        }

        [ICommand]
        public new void ChangeToMainWindow()
        {
            base.ChangeToMainWindow();
        }

    }
}
