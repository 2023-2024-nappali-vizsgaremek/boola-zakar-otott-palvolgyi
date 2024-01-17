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
    public partial class CategoriesViewModel : ObservableObject
    {
        [ObservableProperty]
        private Categories categories;
        [ObservableProperty]
        private List<Categories> categoriess = new List<Categories>();
        [ObservableProperty]
        private ObservableCollection<string> lista = new ObservableCollection<string>();
        public CategoriesViewModel()
        {
            categories = new Categories();
        }
        [RelayCommand]
        public void DoSave(Categories category)
        {
            Lista.Add(Categories.name.ToString());
            Categoriess.Add(category);
            OnPropertyChanged(nameof(Categoriess));
        }
        [RelayCommand]
        public void DoDelete(Categories category)
        {
            Lista.Remove(Categories.name.ToString());
            Categoriess.Remove(category);
            OnPropertyChanged(nameof(Categoriess));
        }
        [RelayCommand]
        public void ChangeToMainWindow()
        {
            MainWindowViewModel.Instance.ChangeToMainWindow();
        }
    }
}