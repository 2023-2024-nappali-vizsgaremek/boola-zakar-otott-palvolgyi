using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using Desktop.Service;

namespace Desktop.Extensions
{
    public static class ApiServiceExtension
    {
        public static void ConfigureApiServices(this IServiceCollection services)
        {
            services.AddScoped<ICurrencyService, CurrencyService>();
            services.AddScoped<ICategoryService, CategoryService>();
            services.AddScoped<ILoginService, LoginService>();
        }
    }
}
