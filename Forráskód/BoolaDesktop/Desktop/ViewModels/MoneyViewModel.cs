using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.ViewModels
{
    public partial class MoneyViewModel:ObservableObject
    {
        [ObservableProperty]
        private Money money;
        [ObservableProperty]
        private ObservableCollection<Money> moneys =new ObservableCollection<Money>();
        public MoneyViewModel()
        {
            Money = new Money();
        }
        [RelayCommand]
        public void DoSave( Money money)
        {
            Moneys.Add(money);
            OnPropertyChanged(nameof(Moneys));
        }
        [RelayCommand]
        public void DoDelete(Money money)
        {
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
