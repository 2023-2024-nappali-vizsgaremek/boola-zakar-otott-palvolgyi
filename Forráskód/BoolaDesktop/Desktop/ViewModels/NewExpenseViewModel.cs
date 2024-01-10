using CommunityToolkit.Mvvm.ComponentModel;
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
    public partial class NewExpenseViewModel : ObservableObject
    {
        [ObservableProperty]
        private NewExpnse expnse;
        [ObservableProperty]
        private ObservableCollection<Category> cat = new ObservableCollection<Category>(new category().categories);
        [ObservableProperty]
        private ObservableCollection<Currency> cur = new ObservableCollection<Currency>(new currency().currencies);
        [ObservableProperty]
        private string kategória;
        [ObservableProperty]
        private string pénznem;
        [ObservableProperty]
        private ObservableCollection<NewExpnse> lista = new ObservableCollection<NewExpnse>();
        private Category _SelectCategory = Category.General;
        private Currency _Currency = Currency.HUF;

        public Category SelectCategory
        {
            get => _SelectCategory;
            set
            {
                SetProperty(ref _SelectCategory, value);
                Expnse.category = SelectCategory;

            }
        }
        public Currency Currency
        {
            get => _Currency;
            set
            {
                SetProperty(ref _Currency, value);
                Expnse.currency = Currency;

            }
        }
        public NewExpenseViewModel(){
            Expnse = new NewExpnse();
            Expnse.category= cat.First();
            Expnse.currency= cur.First();
}
        [RelayCommand]
        public void Add(NewExpnse newExpnse)
        {
            Lista.Add(newExpnse);
            OnPropertyChanged(nameof(Lista));
        }
        [RelayCommand]
        public void ChangeToMainWindow()
        {
            MainWindowViewModel.Instance.ChangeToMainWindow();
        }
    }
} 
