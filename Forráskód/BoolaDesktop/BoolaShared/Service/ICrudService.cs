using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BoolaShared.Service
{
    public interface ICrudService<T>
    {
        public Task<List<T>> GetAll();
        public Task<T> GetById(int id);
        public Task Update(Profile newProfile);
        public Task Delete(int id);
    }
}
