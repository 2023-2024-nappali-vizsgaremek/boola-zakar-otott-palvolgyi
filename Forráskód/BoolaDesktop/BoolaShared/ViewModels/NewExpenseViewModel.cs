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


namespace BoolaShared.ViewModels
{
    public abstract class NewExpenseViewModel : AsyncInitializedViewModel
    {
        private NewExpnse expnse;
        
        protected ObservableCollection<Category> cat = new ObservableCollection<Category>();
    
        protected ObservableCollection<Money> cur = new ObservableCollection<Money>();
     
        private string kategória;
   
        private string pénznem;

        private ObservableCollection<NewExpnse> lista = new ObservableCollection<NewExpnse>();
        private Category _SelectCategory = new Category();
        private Money _Currency = new Money();
        private IExpenseService service;
        public NewExpenseViewModel(IExpenseService expenseService)
        {
            expnse = new NewExpnse();
            expnse.category = new Category();
           
        }

        public void Add(NewExpnse newExpnse)
        {
            lista.Add(newExpnse);
            service.Create(newExpnse);
            OnPropertyChanged(nameof(lista));
        }
        
        public void ChangeToMainWindow()
        {
            MainWindowViewModel.Instance.ChangeToMainWindow();
        }


    }
} 
