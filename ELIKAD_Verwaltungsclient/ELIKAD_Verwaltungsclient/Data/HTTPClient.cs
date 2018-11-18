using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;

namespace ELIKAD_Verwaltungsclient.Data
{
    static class HTTPClient
    {
        static HttpClient client = new HttpClient();
        public static string Token { get; set; }

        public static async Task<List<Member>> GetMembersAsync()
        {
            List<Member> members = null;
            HttpResponseMessage response = await client.GetAsync("members");
            if (response.IsSuccessStatusCode)
            {
                members = await response.Content.ReadAsAsync<List<Member>>();
            }
            return members;
        }

        public static async Task<HttpStatusCode> CreateMemberAsync(Member member)
        {
            HttpResponseMessage response = await client.PostAsJsonAsync(
                "members", member);
            return response.StatusCode;
        }

        public static async Task<HttpStatusCode> DeleteMember(Member member)
        {
            HttpResponseMessage response = await client.DeleteAsync(
                "members/" + member.SVNr);
            return response.StatusCode;
        }

        //static async Task<Product> UpdateProductAsync(Product product)
        //{
        //    HttpResponseMessage response = await client.PutAsJsonAsync(
        //        $"api/products/{product.Id}", product);
        //    response.EnsureSuccessStatusCode();

        //    // Deserialize the updated product from the response body.
        //    product = await response.Content.ReadAsAsync<Product>();
        //    return product;
        //}

        //static async Task<HttpStatusCode> DeleteProductAsync(string id)
        //{
        //    HttpResponseMessage response = await client.DeleteAsync(
        //        $"api/products/{id}");
        //    return response.StatusCode;
        //}

        public static void Init()
        {
            // Update port # in the following line.
            client.BaseAddress = new Uri("http://localhost:8080/api/");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }
    }
}