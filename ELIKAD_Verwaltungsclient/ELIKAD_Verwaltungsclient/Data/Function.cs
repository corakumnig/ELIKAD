using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ELIKAD_Verwaltungsclient.Data
{
    class Function
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Shortcut { get; set; }



        public Function(int id, string name, string shortcut)
        {
            Id = id;
            Name = name;
            Shortcut = shortcut;
        }

        public override bool Equals(object obj)
        {
            var function = obj as Function;
            return function != null &&
                   Id == function.Id;
        }

        public override int GetHashCode()
        {
            return 2108858624 + Id.GetHashCode();
        }
    }
}
