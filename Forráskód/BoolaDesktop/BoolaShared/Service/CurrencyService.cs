using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Json;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Service
{
    public class CurrencyService : ICurrencyService
    {
        private readonly HttpClient? httpClient;
        public CurrencyService(IHttpClientFactory? httpClientFactory)
        {
            httpClient = httpClientFactory?.CreateClient("BoolaApi");
        }
        public async Task<List<Money>> GetAllCurrencys()
        {
            var resp = await httpClient.GetFromJsonAsync<List<Money>>("/api/currency");
            if (resp is null) return new List<Money>();
            return resp.ToList();
        }

        public async Task<Money> GetCurrency(string code)
        {
            var resp = await httpClient.GetStringAsync("/api/currency/" + code);
            if (resp is null) return new Money();
            return new Money(resp, code);
        }
    }
}