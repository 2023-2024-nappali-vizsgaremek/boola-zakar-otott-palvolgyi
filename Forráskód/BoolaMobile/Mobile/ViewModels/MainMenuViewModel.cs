using CommunityToolkit.Mvvm.ComponentModel;
using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.ViewModels
{
    public partial class MainMenuViewModel : ObservableObject
    {
        [ObservableProperty] private MainContent content;

        public MainMenuViewModel()
        {
            content = new MainContent();
        }
    }
}
