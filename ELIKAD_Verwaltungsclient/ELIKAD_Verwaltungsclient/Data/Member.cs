using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ELIKAD_Verwaltungsclient.Data
{
    class Member
    {
        public int Id { get; set; }
        public string Firstname { get; set; }
        public string Lastname { get; set; }
        public DateTime DateOfBirth { get; set; }
        public DateTime DateOfEntry { get; set; }
        public string Phonenumber { get; set; }
        public string Email { get; set; }
        public string SVNr { get; set; }
        public string Gender { get; set; }
        public int IdDepartment { get; set; }

        public Member(int Id, string SVNr, string Firstname, string Lastname, DateTime DateOfBirth, DateTime DateOfEntry, string Phonenumber, string Email, string Gender, int IdDepartment)
        {
            this.Id = Id;
            this.Firstname = Firstname;
            this.Lastname = Lastname;
            this.DateOfBirth = DateOfBirth;
            this.DateOfEntry = DateOfEntry;
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
