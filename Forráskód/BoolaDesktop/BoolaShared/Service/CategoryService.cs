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
    public class CategoryService : ICategoryService
    {
        public HttpClient? HttpClient { get; set; }
        public bool IsClientAvailable => HttpClient != null;
        private readonly HttpClient? httpClient;
        public CategoryService(IHttpClientFactory? httpClientFactory)
        {
            httpClient = httpClientFactory?.CreateClient("BoolaApi");
        }

        public Task<List<Category>> GetAll()
        {
            throw new NotImplementedException();
        }

        public async Task<List<Category>> GetAllCategories()
        {
            var resp = await httpClient.GetFromJsonAsync<List<Category>>("/api/category");
            if (resp is null) return new List<Category>();
            return resp.ToList();
        }

        public Task<Category> GetById(int id)
        {
            throw new NotImplementedException();
        }

        public async Task<Category> GetCategory(string id)
        {
            var resp = await httpClient.GetStringAsync("/api/category/" + id);
            if (resp is null) return new Category();
            return new Category(resp,Convert.ToInt32(id));
        }

    }
}