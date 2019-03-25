using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ELIKAD_Verwaltungsclient.Data
{
    class Location
    {
        public int Id { get; set; }
        public string Housenumber { get; set; }
        public string Street { get; set; }
        public string Postalcode { get; set; }
        public string Village { get; set; }
        public string Region { get; set; }
        public string RegionType { get; set; }

        public Location(int id, string housenumber, string street, string postalcode, string village, string region, string regionType)
        {
            Id = id;
            Housenumber = housenumber;
            Street = street;
            Postalcode = postalcode;
            Village = village;
            Region = region;
            RegionType = regionType;
        }
    }
}
