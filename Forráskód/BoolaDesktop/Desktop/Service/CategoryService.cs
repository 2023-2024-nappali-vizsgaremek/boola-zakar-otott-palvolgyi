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
    public class CategoryService : ICategoryService
    {
        private readonly HttpClient? httpClient;
        public CategoryService(IHttpClientFactory? httpClientFactory)
        {
            httpClient = httpClientFactory?.CreateClient("BoolaApi");
        }
        public async Task<List<Category>> GetAllCategories()
        {
            var resp = await httpClient.GetFromJsonAsync<List<Category>>("/api/currency");
            if (resp is null) return new List<Category>();
            return resp.ToList();
        }

        public async Task<Category> GetCategories(string name)
        {
            var resp = await httpClient.GetStringAsync("/api/categories/" + name);
            if (resp is null) return new Category();
            return new Category(name);
        }

    }
}