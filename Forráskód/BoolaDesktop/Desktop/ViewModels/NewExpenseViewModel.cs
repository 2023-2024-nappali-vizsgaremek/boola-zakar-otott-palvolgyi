using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using Desktop.Models;
using Desktop.Service;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Documents;

namespace Desktop.ViewModels
{
    public partial class NewExpenseViewModel : AsyncInitializedViewModel
    {
        [ObservableProperty]
        private NewExpnse expnse;
        [ObservableProperty]
        private ObservableCollection<Category> cat = new ObservableCollection<Category>(new category().categories);
        [ObservableProperty]
        private ObservableCollection<Categories> cur = new ObservableCollection<Categories>();
        [ObservableProperty]
        private string kategória;
        [ObservableProperty]
        private string pénznem;
        [ObservableProperty]
        private ObservableCollection<NewExpnse> lista = new ObservableCollection<NewExpnse>();
        private Category _SelectCategory = Category.General;
        private Categories _Currency = new Categories();
        private ICurrencyService currencyService;
        public NewExpenseViewModel(ICurrencyService currency)
        {
            currencyService = currency;
            Expnse = new NewExpnse();
            Expnse.category = cat.First();
           
        }
        public override async Task InitializeAsync()
        {
            var c = await currencyService.GetAllCurrencys();
            Cur = new ObservableCollection<Categories>(c);
            
        }
        public Category SelectCategory
        {
            get => _SelectCategory;
            set
            {
                SetProperty(ref _SelectCategory, value);
                Expnse.category = SelectCategory;

            }
        }
        public Categories Currency
        {
            get => _Currency;
            set
            {
                SetProperty(ref _Currency, value);
                Expnse.currency = Currency;

            }
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
