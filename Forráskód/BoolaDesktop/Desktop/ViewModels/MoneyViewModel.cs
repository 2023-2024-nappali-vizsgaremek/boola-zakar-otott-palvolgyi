using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Security.Policy;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace Desktop.ViewModels
{
    public partial class MoneyViewModel : ObservableObject
    {
        [ObservableProperty]
        private Money money;
        [ObservableProperty]
        private List<Money> moneys = new List<Money>();
        [ObservableProperty]
        private ObservableCollection<string> lista = new ObservableCollection<string>();
        public MoneyViewModel()
        {
            Money = new Money();
        }
        [RelayCommand]
        public void DoSave(Money money)
        {
            Lista.Add((Money.name.ToString() + " " + Money.code.ToString()));
            Moneys.Add(money);
            OnPropertyChanged(nameof(Moneys));

        }
        [RelayCommand]
        public void DoDelete(Money money)
        {
            Lista.Remove((Money.name.ToString() + " " + Money.code.ToString()));
            Moneys.Remove(money);
            OnPropertyChanged(nameof(Moneys));


        }
        [RelayCommand]
        public void ChangeToMainWindow()
        {
            MainWindowViewModel.Instance.ChangeToMainWindow();
        }
    }
}