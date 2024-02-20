using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Desktop.Models;
using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;

namespace Desktop.ViewModels
{
    public partial class MainWindowViewModel : BoolaShared.ViewModels.MainWindowViewModel
    {
        [ObservableProperty] 
        private ObservableObject childViewModel;

        public MainWindowViewModel()
        {
            childViewModel = new MainMenuViewModel();
        }

        [ICommand]
        public void ChangeToAddWindow()
        {
            //childViewModel = new newExpensesViewModel();
        }

        [ICommand]
        public void ChangeToSettingsWindow()
        {
            ChildViewModel = new SettingsViewModel();
        }

        public override void ChangeToMainWindow()
        {
            ChildViewModel = new MainMenuViewModel();
        }
    }
}
