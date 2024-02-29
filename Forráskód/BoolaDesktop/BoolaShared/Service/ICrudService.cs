using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BoolaShared.Service
{
    public interface ICrudService<T> : IReadService<T>
    { 
        public Task Update(Profile newProfile);
        public Task Delete(int id);
    }
}
