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
using BoolaShared.Service;

namespace BoolaShared.ViewModels
{
    public abstract class ProfileViewModel : AsyncInitializedViewModel
    {

        private Profile profile;
        private IProfileService profileService;
        protected ObservableCollection<string> lista = new ObservableCollection<string>();
        private List<Profile> lista_ = new List<Profile>();
        public ProfileViewModel(IProfileService profileService)
        {
            profile = new Profile();
            this.profileService = profileService;
        }

        public async Task GetProfiles()
        {
            lista_ = await profileService.GetAll();
            lista = new ObservableCollection<string>(lista_.Select(x => x.Name));
        }

        public void DoSave(Profile profile)
        {
            lista_.Add(profile);
            lista.Add(profile.Name);
            profileService.Create(profile);
            OnPropertyChanged(nameof(profile));
        }

        public void Delete(Profile profile)
        {
            lista_.Remove(profile);
            lista.Remove(profile.Name);
            profileService.Delete(profile.Id);
            OnPropertyChanged(nameof(lista));
        }

        public void ChangeToMainWindow()
        {
            MainWindowViewModel.Instance.ChangeToMainWindow();
        }

    }
}
