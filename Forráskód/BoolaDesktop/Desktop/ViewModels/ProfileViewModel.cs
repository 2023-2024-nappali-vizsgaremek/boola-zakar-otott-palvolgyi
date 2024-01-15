using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Desktop.Models;
using System.Collections.ObjectModel;
using System.Windows;
using System.ComponentModel;
using System.Windows.Controls;

namespace Desktop.ViewModels
{
   partial  class ProfileViewModel : ObservableObject
    {
        [ObservableProperty]
        private Profile profile;
        [ObservableProperty]
       private ObservableCollection<string> lista = new ObservableCollection<string>();
        private List<Profile> lista_ = new List<Profile>(); 
        public ProfileViewModel()
        {
            Profile= new Profile();

        }
        [RelayCommand]
       public void DoSave(Profile profile)
        {
            lista_.Add(profile);
            Lista.Add(Profile.Name);
            OnPropertyChanged(nameof(Lista));
           
        }
        [RelayCommand]
        public void Delete( Profile profile)
        {
            lista_.Remove(profile);
            Lista.Remove(Profile.Name);
            OnPropertyChanged(nameof(Lista));
        }
        [RelayCommand]
        public void ChangeToMainWindow()
        {
            MainWindowViewModel.Instance.ChangeToMainWindow();
        }

    }
}
