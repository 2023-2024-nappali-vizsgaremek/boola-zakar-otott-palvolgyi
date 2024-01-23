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
                    srevices.AddSingleton<MainWindowViewModel>();
                    srevices.AddSingleton<MainWindow>(s => new MainWindow()
                    {
                        DataContext = s.GetRequiredService<MainWindowViewModel>()
                    });
                    srevices.AddSingleton<NewExpenseViewModel>();
                    srevices.AddSingleton<NewExpenseView>(s => new NewExpenseView()
                    {
                        DataContext=s.GetRequiredService<NewExpenseViewModel>()
                    });
                })
                .Build();
        }

    }
}
