using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Json;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Windows;

namespace Desktop.Service
{
    public class LoginService : ILoginService
    {
        private readonly HttpClient? httpClient;
        private bool IsClientAvailable => httpClient != null;
        public LoginService(IHttpClientFactory? httpClientFactory)
        {
            httpClient = httpClientFactory?.CreateClient("BoolaApi");
        }

        public async Task<Account?> GetAccount(Login login)
        {
            if(IsClientAvailable) return null;
            var resp = await httpClient.GetFromJsonAsync<Account>("/api/account/" + login.email);
            return resp;
        }

        public async Task<LoginTokens?> PostLogin(Account account)
        {
            if(!IsClientAvailable) return null;
            var resp = await httpClient!.PostAsJsonAsync("/login", account);
            if(resp is null || resp.StatusCode != System.Net.HttpStatusCode.OK) return null;
            var json = await resp.Content.ReadAsStringAsync();
            var tokens = JsonSerializer.Deserialize<LoginTokens>(json);
            if(tokens is not null)
            {
                AuthService.AuthToken = tokens.access;
                AuthService.RefreshToken = tokens.refresh;
            }
            return tokens; 
        }

        
    }
}
