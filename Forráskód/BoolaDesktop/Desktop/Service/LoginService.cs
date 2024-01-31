using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Json;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace Desktop.Service
{
    public class LoginService : ILoginService
    {
        private readonly HttpClient? httpClient;
        public LoginService(IHttpClientFactory? httpClientFactory)
        {
            httpClient = httpClientFactory?.CreateClient("BoolaApi");
        }
        public async Task<Login> postLogin(Account account)
        {
            var resp = await httpClient.PostAsJsonAsync<Account>("/login",account);
            var json = await resp.Content.ReadAsStringAsync();
            var tokens = await JsonSerializer.DeserializeAsync<LoginTokens>(new )
            return; 
        }
    }
}
