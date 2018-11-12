using ELIKAD_Verwaltungsclient.Data;
using ELIKAD_Verwaltungsclient.UserControls;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace ELIKAD_Verwaltungsclient.Windows
{
    /// <summary>
    /// Interaction logic for AddMember.xaml
    /// </summary>
    public partial class AddMember : Window
    {
        MembersPage membersPage = null;
        public AddMember(MembersPage membersPage)
        {
            InitializeComponent();
            this.membersPage = membersPage;
        }

        private void btnAddMember_Click(object sender, RoutedEventArgs e)
        {
            if (!isDateValid(dpDateOfBirth.SelectedDate))
            {

            }
            if (!isDateValid(dpDateOfEntry.SelectedDate))
            {

            }
            else
            {
                Member m = new Member(txtSvNr.Text, txtFirstname.Text, txtLastname.Text,
                    (DateTime)dpDateOfBirth.SelectedDate, (DateTime)dpDateOfEntry.SelectedDate,
                    txtPhonenumber.Text, txtEmail.Text, getSelectedGender());
                Task<HttpStatusCode> t = Task.Run(() => HTTPClient.CreateMemberAsync(m));
                t.Wait();
                if(t.Result == HttpStatusCode.Created)
                {
                    membersPage.dgMenbers.Items.Add(m);
                    this.Close();
                }
            }
        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private string getSelectedGender()
        {
            string result = "";
            if (radGenderFemale.IsChecked == true)
                result = radGenderFemale.Content.ToString();
            else if (radGenderMale.IsChecked == true)
                result = radGenderMale.Content.ToString();
            return result;
        }

        private bool isDateValid(DateTime? date)
        {
            return date != null && date < DateTime.Now;
        }
    }
}
