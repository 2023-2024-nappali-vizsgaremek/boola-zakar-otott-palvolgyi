using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BoolaShared.Service
{
    public interface ICrudService<TModel,TKey> : IReadService<TModel,TKey>
    { 
        public Task Create(TModel newItem);
        public Task Update(TModel newData);
        public Task Delete(TKey id);
    }
}
