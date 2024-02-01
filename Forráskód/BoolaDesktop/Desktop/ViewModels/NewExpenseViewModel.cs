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
        private ObservableCollection<Category> cat = new ObservableCollection<Category>();
        [ObservableProperty]
        private ObservableCollection<Money> cur = new ObservableCollection<Money>();
        [ObservableProperty]
        private string kategória;
        [ObservableProperty]
        private string pénznem;
        [ObservableProperty]
        private ObservableCollection<NewExpnse> lista = new ObservableCollection<NewExpnse>();
        private Category _SelectCategory = new Category();
        private Money _Currency = new Money();
        private ICurrencyService currencyService;
        public NewExpenseViewModel(ICurrencyService currency)
        {
            currencyService = currency;
            Expnse = new NewExpnse();
            Expnse.category = new Category();
           
        }
        public override async Task InitializeAsync()
        {
            var c = await currencyService.GetAllCurrencys();
            Cur = new ObservableCollection<Money>(c);
            
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
        public Money Currency
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
