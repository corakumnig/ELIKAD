using ELIKAD_Verwaltungsclient.Data;
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
    /// Interaction logic for OperationMember.xaml
    /// </summary>
    public partial class OperationMember : Window
    {
        string[] colnames = { "Id", "Sv-Nr", "Vorname", "Nachname", "Geburtsdatum", "Geschlecht" };
        List<Member> allMembersWasThere = new List<Member>();
        List<Member> allMembersWasntThere = new List<Member>();
        Operation operation;

        public OperationMember(Operation o)
        {
            InitializeComponent();
            try
            {
                operation = o;
                cmbFilterWasThere.ItemsSource = colnames;
                cmbFilterWasThere.SelectedIndex = 0;
                cmbFilterWasntThere.ItemsSource = colnames;
                cmbFilterWasntThere.SelectedIndex = 0;

                Task<List<Member>> ta = Task.Run(() => HTTPClient.GetMembersWhoWereThere(o));
                Task<List<Member>> tn = Task.Run(() => HTTPClient.GetMembersWhoWerentThere(o));
                ta.Wait();
                allMembersWasThere.AddRange(ta.Result);
                dgMembersWasThere.ItemsSource = allMembersWasThere;
                
                tn.Wait();
                allMembersWasntThere.AddRange(tn.Result);
                dgMembersWasntThere.ItemsSource = allMembersWasntThere;
                

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void txtSearchWasThere_TextChanged(object sender, TextChangedEventArgs e)
        {
            IEnumerable<Member> filtered;
            txtSearchWasThere.Text = txtSearchWasThere.Text.Trim();

            if (txtSearchWasThere.Text == "")
            {
                dgMembersWasThere.ItemsSource = allMembersWasThere;
            }
            else
            {
                switch (cmbFilterWasThere.SelectedIndex)
                {
                    case 0:
                        int number;
                        int.TryParse(txtSearchWasThere.Text, out number);
                        filtered = ((IEnumerable<Member>)dgMembersWasThere.ItemsSource).Where(member => member.Id.Equals(number));
                        dgMembersWasThere.ItemsSource = filtered;
                        break;
                    case 1:
                        filtered = ((IEnumerable<Member>)dgMembersWasThere.ItemsSource).Where(member => member.SVNr.StartsWith(txtSearchWasThere.Text));
                        dgMembersWasThere.ItemsSource = filtered;
                        break;
                    case 2:
                        filtered = ((IEnumerable<Member>)dgMembersWasThere.ItemsSource).Where(member => member.Firstname.StartsWith(txtSearchWasThere.Text));
                        dgMembersWasThere.ItemsSource = filtered;
                        break;
                    case 3:
                        filtered = ((IEnumerable<Member>)dgMembersWasThere.ItemsSource).Where(member => member.Lastname.StartsWith(txtSearchWasThere.Text));
                        dgMembersWasThere.ItemsSource = filtered;
                        break;
                    case 4:
                        filtered = ((IEnumerable<Member>)dgMembersWasThere.ItemsSource).Where(member => member.DateOfBirth.ToShortDateString().StartsWith(txtSearchWasThere.Text));
                        dgMembersWasThere.ItemsSource = filtered;
                        break;
                    case 5:
                        filtered = ((IEnumerable<Member>)dgMembersWasThere.ItemsSource).Where(member => member.Gender.StartsWith(txtSearchWasThere.Text));
                        dgMembersWasThere.ItemsSource = filtered;
                        break;

                }
            }
        }

        private void DgMembers_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }

        private void BtnAdd_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                Member m = (Member)dgMembersWasntThere.SelectedItem;
                Task<HttpStatusCode> t = Task.Run(() => HTTPClient.AddMemberToOperation(m, operation));
                t.Wait();
                if (t.Result == HttpStatusCode.OK)
                {
                    allMembersWasThere.Add(m);
                    allMembersWasntThere.Remove(m);
                    dgMembersWasntThere.Items.Refresh();
                    dgMembersWasThere.Items.Refresh();
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

        private void txtSearchWasntThere_TextChanged(object sender, TextChangedEventArgs e)
        {
            IEnumerable<Member> filtered;
            txtSearchWasntThere.Text = txtSearchWasntThere.Text.Trim();

            if (txtSearchWasntThere.Text == "")
            {
                dgMembersWasntThere.ItemsSource = allMembersWasntThere;
            }
            else
            {
                switch (cmbFilterWasntThere.SelectedIndex)
                {
                    case 0:
                        int number;
                        int.TryParse(txtSearchWasntThere.Text, out number);
                        filtered = ((IEnumerable<Member>)dgMembersWasntThere.ItemsSource).Where(member => member.Id.Equals(number));
                        dgMembersWasntThere.ItemsSource = filtered;
                        break;
                    case 1:
                        filtered = ((IEnumerable<Member>)dgMembersWasntThere.ItemsSource).Where(member => member.SVNr.StartsWith(txtSearchWasntThere.Text));
                        dgMembersWasntThere.ItemsSource = filtered;
                        break;
                    case 2:
                        filtered = ((IEnumerable<Member>)dgMembersWasntThere.ItemsSource).Where(member => member.Firstname.StartsWith(txtSearchWasntThere.Text));
                        dgMembersWasntThere.ItemsSource = filtered;
                        break;
                    case 3:
                        filtered = ((IEnumerable<Member>)dgMembersWasntThere.ItemsSource).Where(member => member.Lastname.StartsWith(txtSearchWasntThere.Text));
                        dgMembersWasntThere.ItemsSource = filtered;
                        break;
                    case 4:
                        filtered = ((IEnumerable<Member>)dgMembersWasntThere.ItemsSource).Where(member => member.DateOfBirth.ToShortDateString().StartsWith(txtSearchWasntThere.Text));
                        dgMembersWasntThere.ItemsSource = filtered;
                        break;
                    case 5:
                        filtered = ((IEnumerable<Member>)dgMembersWasntThere.ItemsSource).Where(member => member.Gender.StartsWith(txtSearchWasntThere.Text));
                        dgMembersWasntThere.ItemsSource = filtered;
                        break;

                }
            }
        }

        private void BtnDelete_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                Member m = (Member)dgMembersWasThere.SelectedItem;
                Task<HttpStatusCode> t = Task.Run(() => HTTPClient.DeleteMemberFromOperation(m, operation));
                t.Wait();
                if (t.Result == HttpStatusCode.OK)
                {
                    allMembersWasntThere.Add(m);
                    allMembersWasThere.Remove(m);
                    dgMembersWasntThere.Items.Refresh();
                    dgMembersWasThere.Items.Refresh();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
    }
}
