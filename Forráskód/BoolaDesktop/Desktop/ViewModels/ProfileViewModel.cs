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
       private ObservableCollection<Profile> lista = new ObservableCollection<Profile>();
        public ProfileViewModel()
        {
            Profile= new Profile();

        }
        [RelayCommand]
       public void DoSave(Profile profile)
        {
            
            Lista.Add(profile);
            OnPropertyChanged(nameof(Lista));
            MessageBox.Show(Lista.Count.ToString());
        }
        [RelayCommand]
        public void Delete(Profile profile)
        {
            Lista.Remove(profile);
            OnPropertyChanged(nameof(Lista));
        }

    }
}
