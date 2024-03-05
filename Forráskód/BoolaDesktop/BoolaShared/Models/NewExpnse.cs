using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Models
{
    public class NewExpnse
    {
       
        public Guid Id { get; set; }
        public string Payee { get; set; }
        public Category category { get; set; }
        public Money currency { get; set; }
        public string tag { get; set; }
        public bool Status { get; set; }
        public int Amount { get; set; }
        public string account { get; set; }
        public DateTime date { get; set; }

        public NewExpnse(Guid id,string payee, Category category, Money currency, string tag, bool status, int amount, string account, DateTime date)
        {
            Id = id;
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
            Id = Guid.NewGuid();
            Payee = "Okt";
            this.category = new Category();
            this.currency = new Money();
            this.tag = "Kölcsön";
            Status = false;
            Amount = 0;
            this.date = DateTime.Now;
        }
    }
   
    
    
}
