using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Models
{
    internal class NewExpnse
    {
       

        public string Payee { get; set; }
        public Category category { get; set; }
        public Currency currency { get; set; }
        public string tag { get; set; }
        public bool Status { get; set; }
        public int Amount { get; set; }
        public string account { get; set; }
        public DateTime date { get; set; }

        public NewExpnse(string payee, Category category, Currency currency, string tag, bool status, int amount, string account, DateTime date)
        {
            Payee = payee;
            this.category = category;
            this.currency = currency;
            this.tag = tag;
            Status = status;
            Amount = amount;
            this.account = account;
            this.date = date;
        }
        public NewExpnse()
        {
            Payee = "Okt";
            this.category = Category.General;
            this.currency = Currency.HUF;
            this.tag = "Kölcsön";
            Status = false;
            Amount = 0;
            this.date = DateTime.Now;
        }
    }
    public enum Category
    {
        Travel,
        Transport,
        Entertaiment,
        Health,
        Shopping,
        Services,
        Bills,
        Groceries,
        Finance,
        General
    }
    public class category
    {
        public List<Category> categories = new() {Category.Travel,Category.Transport,Category.Entertaiment,Category.Health,Category.Shopping,Category.Services,Category.Bills,Category.Groceries,Category.Finance,Category.General };
    }
    public enum Currency
    {
        HUF,
        USD,
        EUR
        
    }
    public class currency
    {
        public List<Currency> currencies = new() { Currency.USD, Currency.USD, Currency.USD, Currency.EUR, Currency.HUF };
    }
}
