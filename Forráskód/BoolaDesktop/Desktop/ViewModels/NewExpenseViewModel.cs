using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using Desktop.Models;
using BoolaShared.Service;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Documents;

namespace Desktop.ViewModels
{
    public partial class NewExpenseViewModel : BoolaShared.ViewModels.NewExpenseViewModel
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
        private ICategoryService categoryService;
        public NewExpenseViewModel(ICurrencyService currency,ICategoryService category) : base(currency)
        {
            currencyService = currency;
            categoryService = category;
            Expnse = new NewExpnse();
            Expnse.category = new Category();
           
        }
        public override async Task InitializeAsync()
        {
            List<Money> c = await currencyService.GetAll();
            Cur = new ObservableCollection<Money>(c);
            List<Category> categories = await categoryService.GetAll();
            Cat = new ObservableCollection<Category>(categories);
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
        public new void Add(NewExpnse newExpnse)
        {
            base.Add(newExpnse);
        }

        [RelayCommand]
        public new void ChangeToMainWindow()
        {
            base.ChangeToMainWindow();
        }

    }
} 
