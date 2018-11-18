using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ELIKAD_Verwaltungsclient.Data
{
    class Member
    {
        public string Firstname { get; set; }
        public string Lastname { get; set; }
        public string DateOfBirth { get; set; }
        public string DateOfEntry { get; set; }
        public string Phonenumber { get; set; }
        public string Email { get; set; }
        public string SVNr { get; set; }
        public string Gender { get; set; }
        public int IdDepartment { get; set; }

        public Member(string SVNr, string Firstname, string Lastname, string DateOfBirth, string DateOfEntry, string Phonenumber, string Email, string Gender, int IdDepartment)
        {
            this.Firstname = Firstname;
            this.Lastname = Lastname;
            this.DateOfBirth = DateOfBirth;
            this.DateOfEntry = DateOfBirth;
            this.Phonenumber = Phonenumber;
            this.Email = Email;
            this.SVNr = SVNr;
            this.Gender = Gender;
            this.IdDepartment = IdDepartment;
        }

        public override string ToString()
        {
            return base.ToString();
        }
    }
}
