﻿using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Documents;

namespace Desktop.ViewModels
{
    public partial class SettingsViewModel : BoolaShared.ViewModels.SettingsViewModel
    {
        [ObservableProperty]
        private Settings settings;
        [ObservableProperty]
        private ObservableCollection<nyelvek> nyelv = new ObservableCollection<nyelvek>(new nyelveke().lista);
        [ObservableProperty]
        private string nyelvek;
        [ObservableProperty]
        private string szoveg = "Ha bármilyen kérdése vagy észrevétele van, ne habozzon kapcsolatba lépni velünk! \nA Boola Pénzügyi Alkalmazás ügyfélszolgálata mindig készen áll, hogy segítsen.\r\n\r\nWeboldal: www.boolaapp.com\r\n\r\nE-mail: info@boolaapp.com\r\n\r\nTelefonszám: +36 1 234 5678\r\n\r\nKöszönjük, hogy a Boola alkalmazást választotta pénzügyi szükségletei kielégítésére. \nTartsa velünk az úton a gazdagság és a pénzügyi függetlenség felé!";
        [ObservableProperty]
        private ObservableCollection<Settings> lis = new ObservableCollection<Settings>();

        private nyelvek _Selectnyelv = Models.nyelvek.magyar;

        public nyelvek Selectnyelv
        {
            get => _Selectnyelv;
            set
            {
                SetProperty(ref _Selectnyelv, value); Settings.nyelv = _Selectnyelv;
            }
        }
        public SettingsViewModel()
        {

            Settings = new Settings();
            Settings.nyelv = nyelv.First();

        }
        [ICommand]
        public new void DoSave(Settings newSettings)
        {
            base.DoSave(newSettings);
        }

        [ICommand]
        public new void DoNewSettings()
        {
            base.DoNewSettings();
        }
        [ICommand]
        public new void DoDelete(Settings settingsDoDelete)
        {
            base.DoDelete(settingsDoDelete);
        }
        [ICommand]
        public new void ChangeToMainWindow()
        {
            base.ChangeToMainWindow();
        }
    }
}
