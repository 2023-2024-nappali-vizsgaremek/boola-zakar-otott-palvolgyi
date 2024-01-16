using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Service
{
    public interface ICurrencyService
    {
        public  Task<List<Categories>> GetAllCurrencys();
        public  Task<Categories> GetCurrency(string code);
    }
}
