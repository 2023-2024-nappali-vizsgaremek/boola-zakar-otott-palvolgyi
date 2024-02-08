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
using Desktop.ViewModels;
using Desktop.Views;
using Desktop.Service;
using System.Security.Authentication.ExtendedProtection;
using System.Runtime.CompilerServices;
using Microsoft.Extensions.Http;
using Microsoft.Extensions.Hosting;

namespace Desktop
{
    /// <summary>
    /// Interaction logic for App.xaml
    /// </summary>
    public partial class App : Application
    {
        private readonly bool _login = true;
        private  IHost host;

        protected async override void OnStartup(StartupEventArgs e)
        {
            host = Host.CreateDefaultBuilder()
                .ConfigureServices(srevices =>
                {
                    srevices.ConfigureHttpClient();
                    srevices.ConfigureApiServices();
                    srevices.AddSingleton<NewExpenseViewModel>();
                    srevices.AddSingleton<NewExpenseView>(s => new NewExpenseView()
                    {
                        DataContext = s.GetRequiredService<NewExpenseViewModel>()
                    });
                    srevices.AddSingleton<LoginViewModel>();
                    srevices.AddSingleton<LoginWindow>(s => new LoginWindow()
                    {
                        DataContext = s.GetRequiredService<LoginViewModel>()
                    });
                })
                .Build();
            await host.StartAsync();
                var loginView = host.Services.GetRequiredService<LoginWindow>();
                loginView.Show();
                
         




            }
        private void Application_Startup( object sender,StartupEventArgs e)
        {
            
        }

    }
}
