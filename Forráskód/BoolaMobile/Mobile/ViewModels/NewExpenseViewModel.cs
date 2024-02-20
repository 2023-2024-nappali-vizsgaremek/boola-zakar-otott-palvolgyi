using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Desktop.Service;

namespace Desktop.ViewModels
{
    public partial class NewExpenseViewModel:BoolaShared.ViewModels.NewExpenseViewModel
    {
        public NewExpenseViewModel(ICurrencyService currency) : base(currency)
        {
        }
    }
}
