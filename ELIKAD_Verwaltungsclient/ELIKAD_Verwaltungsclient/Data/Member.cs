using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ELIKAD_Verwaltungsclient.Data
{
    class Member
    {
        public int ID { get; set; }
        public string Firstname { get; set; }
        public string Lastname { get; set; }
        public DateTime DateOfBirth { get; set; }
        public DateTime DateOfEntry { get; set; }
        public string Phonenumber { get; set; }
        public string Email { get; set; }
        public string SVNr { get; set; }

        public Member(int ID, string Firstname, string Lastname, DateTime DateOfBirth, DateTime DateOfEntry, string Phonenumber, string Email, string SVNr)
        {
            this.ID = ID;
            this.Firstname = Firstname;
            this.Lastname = Lastname;
            this.DateOfBirth = DateOfBirth;
            this.DateOfEntry = DateOfBirth;
            this.Phonenumber = Phonenumber;
            this.Email = Email;
            this.SVNr = SVNr;
        }

        public override string ToString()
        {
            return base.ToString();
        }
    }
}
