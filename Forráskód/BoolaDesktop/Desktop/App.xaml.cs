using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using System.Windows;
using Microsoft.Extensions.Http;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.DependencyInjection;
using Desktop.Extensions;

namespace Desktop
{
    /// <summary>
    /// Interaction logic for App.xaml
    /// </summary>
    public partial class App : Application
    {
        private bool _login = false;
        private IHost host;
        public App()
        {
            host=Host.CreateDefaultBuilder()
                .ConfigureServices(srevices =>
                {
                    srevices.ConfigureHttpClient();
                    srevices.ConfigureApiServices();
                })
                .Build();
        }

    }
}
