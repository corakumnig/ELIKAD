using Oracle.ManagedDataAccess.Client;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ELIKAD_Verwaltungsclient.Data
{
    class Database
    {
        OracleConnection connection = null;
        static Database db = null;
        const OracleDependency dep = null;

        private Database()
        { }

        public static Database GetInstance()
        {
            if (db == null)
                db = new Database();
            return db;
        }

        public void Connect()
        {
            //192.168.128.152
            //212.152.179.117
            connection = new OracleConnection("Data Source=192.168.128.152:1521/ora11g;PERSIST SECURITY INFO=True;User ID=d5a08;Password=d5a");
            connection.Open();
        }

        public void Close()
        {
            connection.Close();
        }

        public void InsertFile(string filename, byte[] file)
        {
            string command = "insert into blob values(:1, :2)";
            OracleCommand insertCmd = new OracleCommand(command, connection);
            insertCmd.Parameters.Add("@filename", filename);
            insertCmd.Parameters.Add(new OracleParameter("@file", OracleDbType.Blob, file, System.Data.ParameterDirection.Input));
            insertCmd.Prepare();
            insertCmd.ExecuteNonQuery();
        }

        public List<Member> GetAllMembers()
        {
            string cmd = "SELECT svnr, firstname, lastname, dateofbirth, dateofentry, phonenumber, email, eli_gender.name from eli_member inner join eli_gender on eli_member.gender = eli_gender.id";
            OracleCommand command = new OracleCommand(cmd, connection);
            List<Member> members = new List<Member>();
            OracleDataReader reader = command.ExecuteReader();
            while (reader.Read())
            {
                members.Add(new Member(reader.GetString(0), reader.GetString(1), reader.GetString(2), reader.GetDateTime(3), reader.GetDateTime(4),
                    reader.GetString(5), reader.GetString(6), reader.GetString(7)));
            }
            return members;
        }

        public byte[] LoadFile(object filename)
        {
            string cmd = "SELECT filedata from blob where filename = :1";
            OracleCommand insertCmd = new OracleCommand(cmd, connection);
            insertCmd.Parameters.Add("@filename", filename);
            insertCmd.Prepare();
            byte[] file = null;
            OracleDataReader reader = insertCmd.ExecuteReader();
            while (reader.Read())
            {
                file = reader.GetOracleBlob(0).Value;
            }
            return file;
        }

        public void Archive()
        {

        }

        public void Delete(string filename)
        {
            string command = "delete from blob where filename = :1";
            OracleCommand insertCmd = new OracleCommand(command, connection);
            insertCmd.Parameters.Add("@filename", filename);
            insertCmd.Prepare();
            insertCmd.ExecuteNonQuery();
        }
    }
}
