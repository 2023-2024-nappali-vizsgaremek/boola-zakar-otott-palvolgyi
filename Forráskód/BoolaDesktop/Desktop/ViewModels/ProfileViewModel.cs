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
    public partial class ProfileViewModel : BoolaShared.ViewModels.ProfileViewModel
    {
        [ObservableProperty]
        private Profile profile;
        [ObservableProperty]
        private ObservableCollection<string> lista = new ObservableCollection<string>();
        private List<Profile> lista_ = new List<Profile>();
        public ProfileViewModel()
        {
            Profile = new Profile();

        }
        [ICommand]
        public new void DoSave(Profile profile)
        {
            base.DoSave(profile);
        }
        [ICommand]
        public new void Delete(Profile profile)
        {
            base.Delete(profile);
        }
        [ICommand]
        public new void ChangeToMainWindow()
        {
            base.ChangeToMainWindow();
        }

    }
}
