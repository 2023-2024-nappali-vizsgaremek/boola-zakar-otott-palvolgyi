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
        
        protected ObservableObject childViewModel;
        private NewExpenseViewModel newExpenseViewModel;
        private ProfileViewModel profileViewModel;
        private SettingsViewModel settingsViewModel;

        public static MainWindowViewModel Instance { get; set; }

       
        public MainWindowViewModel()
        {
            Instance ??= this;
        }

        protected MainWindowViewModel(NewExpenseViewModel newExpenseViewModel, ProfileViewModel profileViewModel, SettingsViewModel settingsViewModel)
        { 
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
