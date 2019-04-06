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
    /// Interaction logic for EditMember.xaml
    /// </summary>
    public partial class EditMember : Window
    {
        MembersPage mw;
        public EditMember(MembersPage mw)
        {
            InitializeComponent();
            this.mw = mw;
            Member m = ((Member)mw.dgMembers.SelectedItem);
            txtFirstname.Text = m.Firstname;
            txtLastname.Text = m.Lastname;
            txtSvNr.Text = m.SVNr;
            txtPhonenumber.Text = m.Phonenumber;
            txtEmail.Text = m.Email;
            dpDateOfBirth.SelectedDate = m.DateOfBirth;
            dpDateOfEntry.SelectedDate = m.DateOfEntry;
            txtPin.Password = m.Pin.ToString();
            setGender(m.Gender);
        }

        private void btnEditMember_Click(object sender, RoutedEventArgs e)
        {
            Member m = ((Member)mw.dgMembers.SelectedItem);
            try
            {
                m = new Member(m.Id, txtSvNr.Text, txtFirstname.Text, txtLastname.Text,
                    (DateTime)dpDateOfBirth.SelectedDate, (DateTime)dpDateOfEntry.SelectedDate,
                    txtPhonenumber.Text, txtEmail.Text, getSelectedGender(), HTTPClient.Department.Id, int.Parse(txtPin.Password));
            }
            catch (FormatException)
            {
                MessageBox.Show("Pin muss eine Nummer sein!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            Task<HttpStatusCode> t = Task.Run(() => HTTPClient.EditMemberAsync(m));
            t.Wait();
            if (t.Result == HttpStatusCode.OK)
            {
                this.Close();
            }
           
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

        private void setGender(string gender)
        {
            if (gender == "Female")
                radGenderFemale.IsChecked = true;
            else if (gender == "Male")
                radGenderMale.IsChecked = true;
        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void Window_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            mw.RefreshMembers();
        }
    }
}
