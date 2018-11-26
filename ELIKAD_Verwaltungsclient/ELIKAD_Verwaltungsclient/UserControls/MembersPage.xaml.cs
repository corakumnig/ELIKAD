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
        string[] colnames = { "Sv-Nr", "Vorname", "Nachname", "Geburtsdatum", "Eintrittsdatum", "E-mail", "Telefonnummer", "Geschlecht" };

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
                dgMenbers.ItemsSource = t.Result;

            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message, "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void txtSearch_TextChanged(object sender, TextChangedEventArgs e)
        {
            
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
                if(dgMenbers.SelectedItem == null)
                    MessageBox.Show("Bitte ein Mitglied auswählen", "Info", MessageBoxButton.OK, MessageBoxImage.Information);
                else
                {
                    Member m = (Member)dgMenbers.SelectedItem;
                    Task<HttpStatusCode> t = Task.Run(() => HTTPClient.DeleteMember(m));
                    t.Wait();
                    if (t.Result == HttpStatusCode.OK)
                    {
                       // dgMenbers.Items.Remove(dgMenbers.SelectedItem);
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
    }
}
