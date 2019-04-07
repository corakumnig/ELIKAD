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
        int[] colnames;

        public DepartmentPage(MainWindow mainWindow)
        {
            InitializeComponent();
            try
            {
                this.mainWindow = mainWindow;
                Task<List<int>> tyears = Task.Run(() => HTTPClient.GetYearsOfOps());
                tyears.Wait();
                cmbFilter.ItemsSource = tyears.Result;
                cmbFilter.SelectedIndex = 0;
                HTTPClient.ActualYear = (int)cmbFilter.SelectedItem;
                Task<List<Operation>> t = Task.Run(() => HTTPClient.GetOperationsAsync());
                t.Wait();
                dgOperations.ItemsSource = t.Result;

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }   

        private void DgMenbers_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Operation op = (Operation)dgOperations.SelectedItem;
            Task<Location> t = Task.Run(() => HTTPClient.GetLocationById(op.IdLocation));
            Task<List<Member>> ta = Task.Run(() => HTTPClient.GetMembersWhoWereThere(op));
            t.Wait();
            lblStreet.Content = t.Result.Street;
            lblHousenum.Content = t.Result.Housenumber;
            lblPostal.Content = t.Result.Postalcode;
            lblCity.Content = t.Result.Village;
            ta.Wait();
            lblMembersNum.Content = ta.Result.Count;
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

        private void BtnReport_Click(object sender, RoutedEventArgs e)
        {
            if (dgOperations.SelectedItem == null)
                MessageBox.Show("Sie müssen zuerst ein Mitglied auswählen!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            else
            {
                ReportWindow addMemberWindow = new ReportWindow((Operation)dgOperations.SelectedItem);
                addMemberWindow.Show();
            }
        }

        private void CmbFilter_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            HTTPClient.ActualYear = (int)cmbFilter.SelectedItem;
            Task<List<Operation>> t = Task.Run(() => HTTPClient.GetOperationsAsync());
            t.Wait();
            dgOperations.ItemsSource = t.Result;
        }
    }
}
