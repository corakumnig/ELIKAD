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
            dpDateOfEntry.SelectedDate = DateTime.Now;
            dpDateOfBirth.SelectedDate = DateTime.Now;
            dpDateOfBirth.SelectedDateFormat = DatePickerFormat.Short;
        }

        private void btnAddMember_Click(object sender, RoutedEventArgs e)
        {
            Member m = null;
            try
            {
                m = new Member(0, txtSvNr.Text, txtFirstname.Text, txtLastname.Text,
                    (DateTime)dpDateOfBirth.SelectedDate, (DateTime)dpDateOfEntry.SelectedDate,
                    txtPhonenumber.Text, txtEmail.Text, getSelectedGender(), HTTPClient.Department.Id, int.Parse(txtPin.Password));
            }
            catch (FormatException)
            {
                MessageBox.Show("Pin muss eine Nummer sein!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            Task<HttpStatusCode> t = Task.Run(() => HTTPClient.CreateMemberAsync(m));
            t.Wait();
            if(t.Result == HttpStatusCode.OK)
            {
                this.Close();
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
                result = "Female";
            else if (radGenderMale.IsChecked == true)
                result = "Male";
            return result;
        }

        private bool isDateValid(DateTime? date)
        {
            return date != null && date < DateTime.Now;
        }

        private void Window_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            membersPage.RefreshMembers();
        }
    }
}
