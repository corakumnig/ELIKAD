using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using System.Security.Cryptography;
using System.IO;

namespace ELIKAD_Verwaltungsclient.Data
{
    static class HTTPClient
    {
        private static HttpClient client = new HttpClient();
        public static string Token { get; set; }
        public static Member Member {get; set;}
        public static Department Department { get; set; }
        public static DepartmentStatistics DepartmentStats { get; set; }

        private const string initVector = "sd9vüweäifo3äf";
        private const string passPhrase = "fasafsdfasdfdfasdf6sd5g465234d";
        private const int keysize = 256;

        public static async Task<List<Member>> GetMembersAsync()
        {
            List<Member> members = null;
            HttpResponseMessage response = await client.GetAsync("departments/" + Department.Id + "/members");
            if (response.IsSuccessStatusCode)
            {
                members = await response.Content.ReadAsAsync<List<Member>>();
            }
            return members;
        }

        public static async Task<List<Function>> GetFunctionsAsync()
        {
            List<Function> functions = null;
            HttpResponseMessage response = await client.GetAsync("functions");
            if (response.IsSuccessStatusCode)
            {
                functions = await response.Content.ReadAsAsync<List<Function>>();
            }
            return functions;
        }

        public static async Task<List<Function>> GetFunctionsByMemberAysnc(Member member)
        {
            List<Function> functions = null;
            HttpResponseMessage response = await client.GetAsync("members/" + member.Id + "/functions");
            if (response.IsSuccessStatusCode)
            {
                functions = await response.Content.ReadAsAsync<List<Function>>();
            }
            return functions;
        }

        public static async Task<HttpStatusCode> CreateMemberAsync(Member member)
        {
            HttpResponseMessage response = await client.PostAsJsonAsync(
                "members", member);
            return response.StatusCode;
        }

        public static async Task<HttpStatusCode> EditMemberAsync(Member member)
        {
            HttpResponseMessage response = await client.PutAsJsonAsync(
                "members", member);
            return response.StatusCode;
        }

        public static async Task<HttpStatusCode> DeleteMemberAsync(Member member)
        {
            HttpResponseMessage response = await client.DeleteAsync(
                "members/" + member.Id);
            return response.StatusCode;
        }
        
        public static async Task<HttpStatusCode> LoginAsync(LoginCredentials lc)
        {
            HttpResponseMessage response = await client.PostAsJsonAsync("login/admin", lc);
            if (Token == null || Token.Equals(""))
            {
                Token = response.Headers.GetValues("Token").FirstOrDefault();
                client.DefaultRequestHeaders.Add("Token", Token);
                Properties.Settings.Default.BearerToken = encryptString(Token);
                Properties.Settings.Default.Save();
            }
            Member = await response.Content.ReadAsAsync<Member>();
            return response.StatusCode;
        }

        public static async Task<HttpStatusCode> DeleteFunctionsAsync(Member member, Function function)
        {
            HttpResponseMessage response = new HttpResponseMessage(HttpStatusCode.Accepted);
                response = await client.DeleteAsync(
                    "members/" + member.Id + "/functions/" + function.Id);
            return response.StatusCode;
        }

        public static async Task<HttpStatusCode> AddFunctionsAsync(Member member, Function function)
        {
            HttpResponseMessage response = new HttpResponseMessage(HttpStatusCode.Accepted);
                response = await client.PostAsync(
                    "members/" + member.Id + "/functions/" + function.Id, new StringContent(""));
            return response.StatusCode;
        }

        public static async Task<HttpStatusCode> GetDepartmentStatisticsAsync()
        {
            HttpResponseMessage response = await client.GetAsync("departments/" + Department.Id + "/statistic");
            if (response.IsSuccessStatusCode)
            {
                DepartmentStats = await response.Content.ReadAsAsync<DepartmentStatistics>();
            }
            return response.StatusCode;
        }

        public static async Task<HttpStatusCode> GetDepartmentAsync()
        {
            HttpResponseMessage response = await client.GetAsync("departments/" + Member.IdDepartment);
            if (response.IsSuccessStatusCode)
            {
                Department = (await response.Content.ReadAsAsync<List<Department>>())[0];
            }
            return response.StatusCode;
        }

        public static async Task<HttpStatusCode> LogoutAsync()
        {
            HttpResponseMessage response = await client.DeleteAsync("login/admin");
            return response.StatusCode;
        }

        public static void Init()
        {
            client.BaseAddress = new Uri("https://elikadweb.herokuapp.com/api/");
            //client.BaseAddress = new Uri("http://localhost:8080/api/");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
            if (Properties.Settings.Default.BearerToken != null && !Properties.Settings.Default.BearerToken.Equals(""))
            {
                Token = decryptString(Properties.Settings.Default.BearerToken);
                client.DefaultRequestHeaders.Add("Token", Token);
            }
        }

        private static string encryptString(string plainText)
        {
            byte[] initVectorBytes = Encoding.UTF8.GetBytes(initVector);
            byte[] plainTextBytes = Encoding.UTF8.GetBytes(plainText);
            PasswordDeriveBytes password = new PasswordDeriveBytes(passPhrase, null);
            byte[] keyBytes = password.GetBytes(keysize / 8);
            RijndaelManaged symmetricKey = new RijndaelManaged();
            symmetricKey.Mode = CipherMode.CBC;
            ICryptoTransform encryptor = symmetricKey.CreateEncryptor(keyBytes, initVectorBytes);
            MemoryStream memoryStream = new MemoryStream();
            CryptoStream cryptoStream = new CryptoStream(memoryStream, encryptor, CryptoStreamMode.Write);
            cryptoStream.Write(plainTextBytes, 0, plainTextBytes.Length);
            cryptoStream.FlushFinalBlock();
            byte[] cipherTextBytes = memoryStream.ToArray();
            memoryStream.Close();
            cryptoStream.Close();
            return Convert.ToBase64String(cipherTextBytes);
        }
        private static string decryptString(string cipherText)
        {
            byte[] initVectorBytes = Encoding.UTF8.GetBytes(initVector);
            byte[] cipherTextBytes = Convert.FromBase64String(cipherText);
            PasswordDeriveBytes password = new PasswordDeriveBytes(passPhrase, null);
            byte[] keyBytes = password.GetBytes(keysize / 8);
            RijndaelManaged symmetricKey = new RijndaelManaged();
            symmetricKey.Mode = CipherMode.CBC;
            ICryptoTransform decryptor = symmetricKey.CreateDecryptor(keyBytes, initVectorBytes);
            MemoryStream memoryStream = new MemoryStream(cipherTextBytes);
            CryptoStream cryptoStream = new CryptoStream(memoryStream, decryptor, CryptoStreamMode.Read);
            byte[] plainTextBytes = new byte[cipherTextBytes.Length];
            int decryptedByteCount = cryptoStream.Read(plainTextBytes, 0, plainTextBytes.Length);
            memoryStream.Close();
            cryptoStream.Close();
            return Encoding.UTF8.GetString(plainTextBytes, 0, decryptedByteCount);
        }
    }
}