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
using System.Collections;

namespace BoolaShared.ViewModels
{
    public abstract class ProfileViewModel : ObservableObject
    {

        private Profile profile;

        private ObservableCollection<string> lista = new ObservableCollection<string>();
        private List<Profile> lista_ = new List<Profile>();
        public ProfileViewModel()
        {
            profile = new Profile();

        }


        public void DoSave(Profile profile)
        {
            lista_.Add(profile);
            lista.Add(profile.Name);
            OnPropertyChanged(nameof(profile));
        }

        public void Delete(Profile profile)
        {
            lista_.Remove(profile);
            lista.Remove(profile.Name);
            OnPropertyChanged(nameof(lista));
        }

        public void ChangeToMainWindow()
        {
            MainWindowViewModel.Instance.ChangeToMainWindow();
        }

    }
}
