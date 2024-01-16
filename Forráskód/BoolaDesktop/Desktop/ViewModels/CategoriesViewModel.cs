using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace Desktop.ViewModels
{
    public partial class CategoriesViewModel : ObservableObject
    {
        [ObservableProperty]
        private Categories Categories;
        [ObservableProperty]
        private List<Categories> categoriess = new List<Categories>();
        [ObservableProperty]
        private ObservableCollection<string> lista = new ObservableCollection<string>();
        public CategoriesViewModel()
        {
            Categories = new Categories();
        }
        [RelayCommand]
        public void DoSave(Categories money)
        {
            Lista.Add((Categories.name.ToString() + " " + Categories.code.ToString()));
            categoriess.Add(money);
            OnPropertyChanged(nameof(categoriess));

        }
        [RelayCommand]
        public void DoDelete(Categories money)
        {
            Lista.Remove((Categories.name.ToString() + " " + Categories.code.ToString()));
            categoriess.Remove(money);
            OnPropertyChanged(nameof(categoriess));


        }
        [RelayCommand]
        public void ChangeToMainWindow()
        {
            MainWindowViewModel.Instance.ChangeToMainWindow();
        }
    }
}