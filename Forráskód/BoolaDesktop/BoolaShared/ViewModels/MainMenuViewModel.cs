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
    public abstract class MainMenuViewModel : ObservableObject
    {
      private MainContent content;

        public MainMenuViewModel()
        {
            content = new MainContent();
        }

   
        protected void ChangeToAddWindow()
        {
            MainWindowViewModel.Instance.ChangeToAddWindow();
        }


        protected void ChangeToSettingsWindow()
        {
           MainWindowViewModel.Instance.ChangeToSettingsWindow();
        }

        protected void ChangeToProfilesWindow() 
        {
        MainWindowViewModel.Instance.ChangeToProfilesWindow();
        }

        protected void ChangeToMainWindow()
        {
            MainWindowViewModel.Instance.ChangeToMainWindow();
        }
       
    }
}
