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

namespace Desktop.ViewModels
{
   partial  class ProfileViewModel : ObservableObject
    {
        [ObservableProperty]
        private Profile profile;
        [ObservableProperty]
       private ObservableCollection<Profile> lista2 = new ObservableCollection<Profile>();
        public ProfileViewModel()
        {
            Profile= new Profile();

        }
        [RelayCommand]
       public void DoSave(Profile profile)
        {
            
            Lista2.Add(profile);
            OnPropertyChanged(nameof(Lista2));
            MessageBox.Show("dun");
        }
        [RelayCommand]
        public void Delete(Profile profile)
        {
            Lista2.Remove(profile);
            OnPropertyChanged(nameof(Lista2));
        }

    }
}
