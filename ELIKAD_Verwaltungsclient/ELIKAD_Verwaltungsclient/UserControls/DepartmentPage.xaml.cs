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
    public partial class DepartmentPage : UserControl
    {
        MainWindow mainWindow;
        string[] colnames = { "Id", "Sv-Nr", "Vorname", "Nachname", "Geburtsdatum", "Eintrittsdatum", "E-mail", "Telefonnummer", "Geschlecht" };

        public DepartmentPage(MainWindow mainWindow)
        {
            InitializeComponent();
            try
            {
                this.mainWindow = mainWindow;
                cmbFilter.ItemsSource = colnames;
                cmbFilter.SelectedIndex = 0;
                Task<List<Operation>> t = Task.Run(() => HTTPClient.GetOperationsAsync());
                t.Wait();
                dgOperations.ItemsSource = t.Result;

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void btnAddNewMember_Click(object sender, RoutedEventArgs e)
        {
           // AddMember addMemberWindow = new AddMember(this);
            //addMemberWindow.Show();
        }

        private void BtnDeleteMember_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                if (dgOperations.SelectedItem == null)
                    MessageBox.Show("Bitte ein Mitglied auswählen", "Info", MessageBoxButton.OK, MessageBoxImage.Information);
                else
                {
                    Operation m = (Operation)dgOperations.SelectedItem;
                    Task<HttpStatusCode> t = null;//Task.Run(() => HTTPClient.DeleteMemberAsync(m));
                    t.Wait();
                    if (t.Result == HttpStatusCode.OK)
                    {
                        //dgMenbers.Items.Remove(dgMenbers.SelectedItem);
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void DgMenbers_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }

        private void BtnAddFunction_Click(object sender, RoutedEventArgs e)
        {
            //FunctionsWindow fw = new FunctionsWindow(this);
            //fw.Show();
        }

        private void BtnEdit_Click(object sender, RoutedEventArgs e)
        {
            //if (dgMembers.SelectedItem == null)
            //{
            //    MessageBox.Show("Sie müssen zuerst ein Mitglied auswählen!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            //}
            //else
            //{
            //    //EditMember em = new EditMember(this);
            //    //em.Show();
            //}
        }

        private void BtnAddMember_Click(object sender, RoutedEventArgs e)
        {

            if (dgOperations.SelectedItem == null)
                MessageBox.Show("Sie müssen zuerst ein Mitglied auswählen!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            else
            {
                OperationMember addMemberWindow = new OperationMember((Operation)dgOperations.SelectedItem);
                addMemberWindow.Show();
            }
        }
    }
}
