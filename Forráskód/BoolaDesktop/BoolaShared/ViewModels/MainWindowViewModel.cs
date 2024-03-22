using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Desktop.Models;
using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using System.Windows;
using BoolaShared.Service;

namespace BoolaShared.ViewModels
{
    public abstract class MainWindowViewModel : AsyncInitializedViewModel
    {

        protected abstract ObservableObject OpenedChildViewModel { get; set; }
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
            OpenedChildViewModel = newExpenseViewModel;
        }


  
        public void ChangeToSettingsWindow()
        {
            OpenedChildViewModel = settingsViewModel;
        }
      
        public void ChangeToProfilesWindow()
        {
            OpenedChildViewModel = profileViewModel;
        }

        public abstract void ChangeToMainWindow();

    }
}
