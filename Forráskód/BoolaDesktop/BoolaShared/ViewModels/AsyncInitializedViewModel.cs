using CommunityToolkit.Mvvm.ComponentModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BoolaShared.ViewModels
{
     public abstract class AsyncInitializedViewModel: ObservableObject
    {

       public virtual Task InitializeAsync() { return Task.CompletedTask; }
    }
}
