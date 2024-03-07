using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http.Json;
using System.Text;
using System.Threading.Tasks;

namespace BoolaShared.Service
{
    public class ExpenseService : IExpenseService
    {
        public HttpClient? HttpClient { get; set; }
        public bool IsClientAvailable => HttpClient != null;

        public ExpenseService(IHttpClientFactory? httpClientFactory)
        {
            HttpClient = httpClientFactory.CreateClient("BoolaApi");
            HttpClient.DefaultRequestHeaders.Authorization = new System.Net.Http.Headers.AuthenticationHeaderValue("bearer", AuthService.AuthToken);
        }

        public async Task Delete(Guid id)
        {
            if (!IsClientAvailable) return;
            var resp = await HttpClient.DeleteAsync("api/expense/" + id);
            resp.EnsureSuccessStatusCode();
        }

        public async Task<List<NewExpnse>> GetAll()
        {
            if (!IsClientAvailable) return new List<NewExpnse>();
            var resp = await HttpClient.GetFromJsonAsync<List<NewExpnse>>("api/expense");
            if (resp == null) return new List<NewExpnse>();
            return resp;
        }

        public async Task<NewExpnse> GetById(Guid id)
        {
            if (!IsClientAvailable) return null;
            var resp = await HttpClient.GetFromJsonAsync<NewExpnse>("api/expense/" + id);
            return resp;
        }

        public async Task Update(NewExpnse newData)
        {
            if(!IsClientAvailable) return;
            var resp = await HttpClient.PutAsJsonAsync<NewExpnse>("api/expense/" + newData.Id,newData);
            resp.EnsureSuccessStatusCode();
        }

        public async Task Create(NewExpnse newExpnse)
        {
            if(!IsClientAvailable) return;
            var resp = await HttpClient.PostAsJsonAsync("api/expense",newExpnse);
            resp.EnsureSuccessStatusCode();
        }
    }
}
