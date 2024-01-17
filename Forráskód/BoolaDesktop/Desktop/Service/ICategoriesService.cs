using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Service
{
    public interface ICategoriesService
    {
        public Task<List<Money>> GetAllCategories();
        public Task<Money> GetCategories(string code);
    }
}