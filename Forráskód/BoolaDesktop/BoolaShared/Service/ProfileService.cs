using Desktop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BoolaShared.Service
{
    public class ProfileService : IProfileService
    {
        private readonly HttpClient? httpClient;

        public ProfileService(IHttpClientFactory? httpClientFactory)
        {
            httpClient = httpClientFactory?.CreateClient("BoolaApi");
        }

        public Task Delete(int id)
        {
            throw new NotImplementedException();
        }

        public Task<List<Profile>> GetAll()
        {
            throw new NotImplementedException();
        }

        public Task<Profile> GetById(int id)
        {
            throw new NotImplementedException();
        }

        public Task Update(Profile newProfile)
        {
            throw new NotImplementedException();
        }
    }
}
