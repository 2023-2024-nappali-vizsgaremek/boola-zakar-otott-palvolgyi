using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BoolaShared.Service
{
    public interface IReadService<T>
    {
        public Task<List<T>> GetAll();
        public Task<T> GetById(int id);
    }
}
