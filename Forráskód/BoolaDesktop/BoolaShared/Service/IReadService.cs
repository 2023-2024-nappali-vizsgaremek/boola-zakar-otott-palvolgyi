using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace BoolaShared.Service
{
    public interface IReadService<TModel,TKey>
    {
        protected HttpClient? HttpClient { get; set;}

        public Task<List<TModel>> GetAll();
        public Task<TModel> GetById(TKey id);

        protected abstract bool IsClientAvailable { get;}
    }
}
