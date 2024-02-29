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
using System.Security.Authentication.ExtendedProtection;
using System.Runtime.CompilerServices;

namespace Desktop
{
    /// <summary>
    /// Interaction logic for App.xaml
    /// </summary>
    public partial class App : Application
    {
        private readonly bool _login = true;
        private IHost host;

        protected async override void OnStartup(StartupEventArgs e)
        {
            host = Host.CreateDefaultBuilder()
                .ConfigureServices(srevices =>
                {
                    srevices.ConfigureHttpClient();
                    srevices.ConfigureApiServices();
                    srevices.AddSingleton<MainWindowViewModel>();
                    srevices.AddSingleton(s => new MainWindow()
                    {
                        DataContext = s.GetRequiredService<MainWindowViewModel>()
                    });
                    srevices.AddSingleton<NewExpenseViewModel>();
                    srevices.AddSingleton<NewExpenseView>(s => new NewExpenseView()
                    {
                        DataContext = s.GetRequiredService<NewExpenseViewModel>()
                    });
                    srevices.AddSingleton<LoginViewModelDesktop>();
                    srevices.AddSingleton<LoginWindow>(s => new LoginWindow()
                    {
                        DataContext = s.GetRequiredService<LoginViewModelDesktop>()
                    });
                    srevices.AddSingleton<ProfileViewModel>();
                    srevices.AddSingleton(s => new UserControl1()
                    {
                        DataContext = s.GetRequiredService<ProfileViewModel>()
                    });
                    srevices.AddSingleton<SettingsViewModel>();
                    srevices.AddSingleton(s => new SettingsView()
                    {
                        DataContext = s.GetRequiredService<SettingsViewModel>()
                    });
                })
                .Build();
            await host.StartAsync();
            var loginView = host.Services.GetRequiredService<LoginWindow>();
            loginView.Show();
            loginView.IsVisibleChanged += (_,_) =>
            {
                if(!loginView.IsVisible)
                {
                    var mainView = host.Services.GetRequiredService<MainWindow>();
                    mainView.Show();
                    loginView.Close();
                }
            };





        }
        private void Application_Startup(object sender, StartupEventArgs e)
        {

        }

    }
}
