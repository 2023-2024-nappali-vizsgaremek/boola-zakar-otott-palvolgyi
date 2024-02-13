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


namespace BoolaShared.ViewModels
{
    public abstract class NewExpenseViewModel : AsyncInitializedViewModel
    {
        private NewExpnse expnse;
        
        private ObservableCollection<Category> cat = new ObservableCollection<Category>();
    
        private ObservableCollection<Money> cur = new ObservableCollection<Money>();
     
        private string kategória;
   
        private string pénznem;

        private ObservableCollection<NewExpnse> lista = new ObservableCollection<NewExpnse>();
        private Category _SelectCategory = new Category();
        private Money _Currency = new Money();
        private ICurrencyService currencyService;
        public NewExpenseViewModel(ICurrencyService currency)
        {
            currencyService = currency;
            expnse = new NewExpnse();
            expnse.category = new Category();
           
        }
        public override async Task InitializeAsync()
        {
            var c = await currencyService.GetAllCurrencys();
            cur = new ObservableCollection<Money>(c);
            
        }
        public Category SelectCategory
        {
            get => _SelectCategory;
            set
            {
                SetProperty(ref _SelectCategory, value);
                expnse.category = SelectCategory;

            }
        }
        public Money Currency
        {
            get => _Currency;
            set
            {
                SetProperty(ref _Currency, value);
                expnse.currency = Currency;

            }
        }
       
          

        
    }
} 
