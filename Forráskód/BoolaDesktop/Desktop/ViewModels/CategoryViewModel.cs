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
    public partial class CategoryViewModel : ObservableObject
    {
        [ObservableProperty]
        private Category category;
        [ObservableProperty]
        private List<Category> categories = new List<Category>();
        [ObservableProperty]
        private ObservableCollection<string> lista = new ObservableCollection<string>();
        public CategoryViewModel()
        {
            category = new Category();
        }
        [RelayCommand]
        public void DoSave(Category category)
        {
            Lista.Add(category.ToString());
            Categories.Add(category);
            OnPropertyChanged(nameof(Categories));
        }
        [RelayCommand]
        public void DoDelete(Category category)
        {
            Lista.Remove(category.ToString());
            Categories.Add(category);
            OnPropertyChanged(nameof(Categories));
        }
        [RelayCommand]
        public void ChangeToMainWindow()
        {
            MainWindowViewModel.Instance.ChangeToMainWindow();
        }
    }
}