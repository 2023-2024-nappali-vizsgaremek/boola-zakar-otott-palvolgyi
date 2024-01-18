using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Extensions
{
    public static class HttpClientExtension
    {
        public static void ConfigureHttpClient(this IServiceCollection services)
        {
            services.AddHttpClient("BoolaApi", options => new Uri("https://localhost:8080"));
                }
    }
}
