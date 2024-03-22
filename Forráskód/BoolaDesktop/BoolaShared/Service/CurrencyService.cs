using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Json;
using System.Text;
using System.Threading.Tasks;

namespace BoolaShared.Service
{
    public class CurrencyService : ICurrencyService
    {
        public HttpClient? HttpClient { get; set; }
        public bool IsClientAvailable => HttpClient != null;
        public CurrencyService(IHttpClientFactory? httpClientFactory)
        {
            HttpClient = httpClientFactory?.CreateClient("BoolaApi");
        }

        public async Task<List<Money>> GetAll()
        {
            var resp = await HttpClient.GetFromJsonAsync<List<Money>>("/api/currency");
            if (resp is null) return new List<Money>();
            return resp.ToList();
            throw new NotImplementedException();
        }

        public Task<Money> GetById(string id)
        {
            throw new NotImplementedException();
        }

        public async Task<Money> GetCurrency(string code)
        {
            var resp = await HttpClient.GetStringAsync("/api/currency/" + code);
            if (resp is null) return new Money();
            return new Money(resp, code);
        }
    }
}