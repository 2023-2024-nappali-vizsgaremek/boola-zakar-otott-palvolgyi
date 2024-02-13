using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace BoolaShared.ViewModels
{
    public abstract class SettingsViewModel : ObservableObject
    {
      
        private Settings settings;

        private ObservableCollection<nyelvek> nyelv = new ObservableCollection<nyelvek>(new nyelveke().lista);

        private string nyelvek;

        private string szoveg = "Ha bármilyen kérdése vagy észrevétele van, ne habozzon kapcsolatba lépni velünk! \nA Boola Pénzügyi Alkalmazás ügyfélszolgálata mindig készen áll, hogy segítsen.\r\n\r\nWeboldal: www.boolaapp.com\r\n\r\nE-mail: info@boolaapp.com\r\n\r\nTelefonszám: +36 1 234 5678\r\n\r\nKöszönjük, hogy a Boola alkalmazást választotta pénzügyi szükségletei kielégítésére. \nTartsa velünk az úton a gazdagság és a pénzügyi függetlenség felé!";
     
        private ObservableCollection<Settings> lis = new ObservableCollection<Settings>();

        private nyelvek _Selectnyelv = Models.nyelvek.magyar;

        public nyelvek Selectnyelv
        {
            get => _Selectnyelv;
            set
            {
                SetProperty(ref _Selectnyelv, value); settings.nyelv = _Selectnyelv;
            }
        }
        public SettingsViewModel()
        {

            settings = new Settings();
            settings.nyelv = nyelv.First();

        }
       
    }
}
