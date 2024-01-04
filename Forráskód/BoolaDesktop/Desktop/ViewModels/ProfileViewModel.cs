using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Desktop.Models;

namespace Desktop.ViewModels
{
   partial  class ProfileViewModel : ObservableObject
    {
        [ObservableProperty]
        private ProfileViewModel profile;
        public ProfileViewModel()
        {
            profile = new ProfileViewModel();
        }

        
    }
}
