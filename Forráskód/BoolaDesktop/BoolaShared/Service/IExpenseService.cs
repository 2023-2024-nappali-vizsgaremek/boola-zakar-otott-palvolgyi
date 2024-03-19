using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BoolaShared.Service
{
    public interface IExpenseService : ICrudService<NewExpnse,Guid>
    {
    }
}
