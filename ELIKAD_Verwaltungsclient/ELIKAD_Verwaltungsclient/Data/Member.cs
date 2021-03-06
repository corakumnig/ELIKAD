﻿using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ELIKAD_Verwaltungsclient.Data
{
    public class Member
    {
        public int Id { get; set; }
        public string Firstname { get; set; }
        public string Lastname { get; set; }
        public DateTime DateOfBirth { get; set; }
        public DateTime DateOfEntry { get; set; }
        public string Phonenumber { get; set; }
        public string Email { get; set; }
        public string SvNr { get; set; }
        public string Gender { get; set; }
        public int? IdDepartment { get; set; }
        public string Pin { get; set; }

        public Member(int Id, string SvNr, string Firstname, string Lastname, DateTime DateOfBirth, DateTime DateOfEntry, string Phonenumber, string Email, string Gender, int? IdDepartment, string Pin)
        {
            this.Id = Id;
            this.Firstname = Firstname;
            this.Lastname = Lastname;
            this.DateOfBirth = DateOfBirth;
            this.DateOfEntry = DateOfEntry;
            this.Phonenumber = Phonenumber;
            this.Email = Email;
            this.SvNr = SvNr;
            this.Gender = Gender;
            this.IdDepartment = IdDepartment;
            this.Pin = Pin;
        }

        public override string ToString()
        {
            return base.ToString();
        }

        public override bool Equals(object obj)
        {
            var member = obj as Member;
            return member != null &&
                   Id == member.Id;
        }

        public override int GetHashCode()
        {
            return 2108858624 + Id.GetHashCode();
        }
    }
}
