using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ELIKAD_Verwaltungsclient.Data
{
    class Department
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Organization { get; set; }
        public Location Location { get; set; }

        public Department(int id, string name, string organization, Location location)
        {
            Id = id;
            Name = name;
            Organization = organization;
            Location = location;
        }
    }
}
