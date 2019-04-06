using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ELIKAD_Verwaltungsclient.Data
{
    public class Operation
    {
        public int Id { get; set; }
        public DateTime StartDatetime { get; set; }
        public DateTime? EndDatetime { get; set; }
        public string Text { get; set; }
        public string Type { get; set; }
        public string Alarmlevel { get; set; }
        public string ControlcenterName { get; set; }
        public int IdLocation { get; set; }

        public Operation(int id, DateTime startDatetime, DateTime endDatetime, string reason, string text, string alarmlevel, string controlcenterName, string type, int idLocation)
        {
            Id = id;
            StartDatetime = startDatetime;
            EndDatetime = endDatetime;
            Text = text;
            Alarmlevel = alarmlevel;
            ControlcenterName = controlcenterName;
            Type = type;
            IdLocation = IdLocation;
        }

        public Operation() { }
    }
}
