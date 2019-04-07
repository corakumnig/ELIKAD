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
    /// Interaction logic for AddExistingMember.xaml
    /// </summary>
    public partial class AddExistingMember : Window
    {
        string[] colnames = { "Id", "Sv-Nr", "Vorname", "Nachname", "Geburtsdatum", "Geschlecht" };
        List<Member> allMembers = new List<Member>();
        private MembersPage memberPage;

        public AddExistingMember(MembersPage memberPage)
        {
            InitializeComponent();
            this.memberPage = memberPage;
            cmbMemberFilter.ItemsSource = colnames;
            cmbMemberFilter.SelectedIndex = 0;

            Task<List<Member>> t = Task.Run(() => HTTPClient.GetMembersWithoutDepartment());
            t.Wait();
            allMembers = t.Result;
            dgMembers.ItemsSource = allMembers;
        }

        private void TxtSearchMember_TextChanged(object sender, TextChangedEventArgs e)
        {
            IEnumerable<Member> filtered;
            txtSearchMember.Text = txtSearchMember.Text.Trim();

            if (txtSearchMember.Text == "")
            {
                dgMembers.ItemsSource = allMembers;
            }
            else
            {
                switch (cmbMemberFilter.SelectedIndex)
                {
                    case 0:
                        int number;
                        int.TryParse(txtSearchMember.Text, out number);
                        filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.Id.Equals(number));
                        dgMembers.ItemsSource = filtered;
                        break;
                    case 1:
                        filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.SvNr.StartsWith(txtSearchMember.Text));
                        dgMembers.ItemsSource = filtered;
                        break;
                    case 2:
                        filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.Firstname.StartsWith(txtSearchMember.Text));
                        dgMembers.ItemsSource = filtered;
                        break;
                    case 3:
                        filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.Lastname.StartsWith(txtSearchMember.Text));
                        dgMembers.ItemsSource = filtered;
                        break;
                    case 4:
                        filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.DateOfBirth.ToShortDateString().StartsWith(txtSearchMember.Text));
                        dgMembers.ItemsSource = filtered;
                        break;
                    case 5:
                        filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.Gender.StartsWith(txtSearchMember.Text));
                        dgMembers.ItemsSource = filtered;
                        break;
                }
            }
        }

        private void BtnAdd_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                Member m = (Member)dgMembers.SelectedItem;
                m.IdDepartment = HTTPClient.Department.Id;
                Task<HttpStatusCode> t = Task.Run(() => HTTPClient.EditMemberAsync(m));
                t.Wait();
                if (t.Result == HttpStatusCode.OK)
                {
                    allMembers.Remove(m);
                    dgMembers.Items.Refresh();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void BtnCancel_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void Window_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            memberPage.RefreshMembers();
        }
    }
}
