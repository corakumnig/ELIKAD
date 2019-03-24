using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ELIKAD_Verwaltungsclient.Data
{
    class DepartmentStatistics
    {
        public int NumberOfOperations { get; set; }
        public int NumberOfMembers { get; set; }
        public int NumberOfMembersInOperations { get; set; }

        public DepartmentStatistics(int numberOfOperations, int numberOfMembers, int numberOfMembersInOperations)
        {
            NumberOfOperations = numberOfOperations;
            NumberOfMembers = numberOfMembers;
            NumberOfMembersInOperations = numberOfMembersInOperations;
        }
    }
}
