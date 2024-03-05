﻿using Desktop.Models;
using BoolaShared.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http.Json;
using System.Text;
using System.Threading.Tasks;

namespace BoolaShared.Service
{
    public class ProfileService : IProfileService
    {
        public HttpClient? HttpClient { get; set; }
        public bool IsClientAvailable => HttpClient != null;

        public ProfileService(IHttpClientFactory? httpClientFactory)
        {
           HttpClient = httpClientFactory?.CreateClient("BoolaApi");
           HttpClient.DefaultRequestHeaders.Authorization = new System.Net.Http.Headers.AuthenticationHeaderValue("bearer",AuthService.AuthToken);
        }

        

        public async Task Delete(Guid id)
        {
            if(!IsClientAvailable) return;
            var resp = await HttpClient.DeleteAsync("api/profile/" + id);
            resp.EnsureSuccessStatusCode();
        }

        public async Task<List<Profile>> GetAll()
        {
            if(!IsClientAvailable) return new List<Profile>();
            var resp = await HttpClient.GetFromJsonAsync<List<Profile>>("api/profile");
            if(resp is null) return new List<Profile>();
            return resp;
        }

        public async Task<Profile> GetById(Guid id)
        {
            if(!IsClientAvailable) return null;
            var resp = await HttpClient.GetFromJsonAsync<Profile>("api/profile/" + id);
            return resp;
        }

        public async Task Update(Profile profile)
        {
            if(!IsClientAvailable) return;
            var resp = await HttpClient.PutAsJsonAsync("api/profile/" + profile.Id,profile);
            resp.EnsureSuccessStatusCode();
        }

        async Task ICrudService<Profile, Guid>.Create(Profile newItem)
        {
            if (!IsClientAvailable) return;
            var resp = await HttpClient.PostAsJsonAsync<Profile>("api/profile",newItem);
            resp.EnsureSuccessStatusCode();
        }
    }
}
