using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http.Headers;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Extensions
{
    public static class HttpClientExtension
    {
        public static void ConfigureHttpClient(this IServiceCollection services)
        {
            services.AddHttpClient("BoolaApi", options =>
            {
                options.BaseAddress = new Uri("http://localhost:8080");
            }
                );
        }
    }
}
