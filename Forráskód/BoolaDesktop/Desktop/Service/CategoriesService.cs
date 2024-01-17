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
    public class CategoriesService : ICategoriesService
    {
        private readonly HttpClient? httpClient;
        public CategoriesService(IHttpClientFactory? httpClientFactory)
        {
            httpClient = httpClientFactory?.CreateClient("BoolaApi");
        }
        public async Task<List<Categories>> GetAllCategories()
        {
            var resp = await httpClient.GetFromJsonAsync<List<Categories>>("/api/currency");
            if (resp is null) return new List<Categories>();
            return resp.ToList();
        }

        public async Task<Categories> GetCategories(string name)
        {
            var resp = await httpClient.GetStringAsync("/api/categories/" + name);
            if (resp is null) return new Categories();
            return new Categories(name);
        }

    }
}