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
   public abstract  class ProfileViewModel : ObservableObject
    {
   
        private Profile profile;

       private ObservableCollection<string> lista = new ObservableCollection<string>();
        private List<Profile> lista_ = new List<Profile>(); 
        public ProfileViewModel()
        {
            profile= new Profile();

        }
       

    }
}
