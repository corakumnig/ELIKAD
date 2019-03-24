using ELIKAD_Verwaltungsclient.Data;
using ELIKAD_Verwaltungsclient.Windows;
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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace ELIKAD_Verwaltungsclient.UserControls
{
    /// <summary>
    /// Interaction logic for Members.xaml
    /// </summary>
    public partial class MembersPage : UserControl
    {
        MainWindow mainWindow;
        Database db = Database.GetInstance();
        string[] colnames = {"Id", "Sv-Nr", "Vorname", "Nachname", "Geburtsdatum", "Eintrittsdatum", "E-mail", "Telefonnummer", "Geschlecht" };

        public MembersPage(MainWindow mainWindow)
        {
            InitializeComponent();
            try
            {
                this.mainWindow = mainWindow;
                cmbFilter.ItemsSource = colnames;
                cmbFilter.SelectedIndex = 0;
                Task<List<Member>> t = Task.Run(() => HTTPClient.GetMembersAsync());
                t.Wait();
                dgMembers.ItemsSource = t.Result;

            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message, "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void txtSearch_TextChanged(object sender, TextChangedEventArgs e)
        {
            IEnumerable<Member> filtered;
            txtSearch.Text = txtSearch.Text.Trim();
            switch (cmbFilter.SelectedIndex)
            { 
                case 0:
                    int number;
                    int.TryParse(txtSearch.Text, out number);
                    filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.Id.Equals(number));
                    dgMembers.ItemsSource = filtered;
                    break;
                case 1:
                    filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.SVNr.StartsWith(txtSearch.Text));
                    dgMembers.ItemsSource = filtered;
                    break;
                case 2:
                    filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.Firstname.StartsWith(txtSearch.Text));
                    dgMembers.ItemsSource = filtered;
                    break;
                case 3:
                    filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.Lastname.StartsWith(txtSearch.Text));
                    dgMembers.ItemsSource = filtered;
                    break;
                case 4:
                   filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.DateOfBirth.ToShortDateString().StartsWith(txtSearch.Text));
                    dgMembers.ItemsSource = filtered;
                    break;
                case 5:
                    filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.DateOfEntry.ToShortDateString().StartsWith(txtSearch.Text));
                    dgMembers.ItemsSource = filtered;
                    break;
                case 6:
                    filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.Email.StartsWith(txtSearch.Text));
                    dgMembers.ItemsSource = filtered;
                    break;
                case 7:
                    filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.Phonenumber.StartsWith(txtSearch.Text));
                    dgMembers.ItemsSource = filtered;
                    break;
                case 8:
                    filtered = ((IEnumerable<Member>)dgMembers.ItemsSource).Where(member => member.Gender.StartsWith(txtSearch.Text));
                    dgMembers.ItemsSource = filtered;
                    break;
                
            }
        }

        private void btnAddNewMember_Click(object sender, RoutedEventArgs e)
        {
            AddMember addMemberWindow = new AddMember(this);
            addMemberWindow.Show();
        }

        private void BtnDeleteMember_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                if(dgMembers.SelectedItem == null)
                    MessageBox.Show("Bitte ein Mitglied auswählen", "Info", MessageBoxButton.OK, MessageBoxImage.Information);
                else
                {
                    Member m = (Member)dgMembers.SelectedItem;
                    Task<HttpStatusCode> t = Task.Run(() => HTTPClient.DeleteMemberAsync(m));
                    t.Wait();
                    if (t.Result == HttpStatusCode.OK)
                    {
                        //dgMenbers.Items.Remove(dgMenbers.SelectedItem);
                    }
                }
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message, "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void DgMenbers_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
        
        }

        private void BtnAddFunction_Click(object sender, RoutedEventArgs e)
        {
            FunctionsWindow fw = new FunctionsWindow(this);
            fw.Show();
        }

        private void BtnEdit_Click(object sender, RoutedEventArgs e)
        {
            if (dgMembers.SelectedItem == null)
            {
                MessageBox.Show("Sie müssen zuerst ein Mitglied auswählen!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            else
            {
                EditMember em = new EditMember(this);
                em.Show();
            }
        }
    }
}
