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

namespace BoolaShared.ViewModels
{
    public abstract class MainWindowViewModel : AsyncInitializedViewModel
    {
        
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

        protected MainWindowViewModel(LoginViewModel childViewModel, NewExpenseViewModel newExpenseViewModel, ProfileViewModel profileViewModel, SettingsViewModel settingsViewModel)
        {
           
            this.childViewModel = childViewModel;
            Instance ??= this;
            this.newExpenseViewModel = newExpenseViewModel;
            this.profileViewModel = profileViewModel;
            this.settingsViewModel = settingsViewModel;
        }

        public void ChangeToAddWindow()
        {
            childViewModel = newExpenseViewModel;
        }


  
        public void ChangeToSettingsWindow()
        {
            childViewModel = settingsViewModel;
        }
      
        public void ChangeToProfilesWindow()
        {
            childViewModel = profileViewModel;
        }

        public abstract void ChangeToMainWindow();

    }
}
