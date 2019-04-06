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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace ELIKAD_Verwaltungsclient.UserControls
{
    /// <summary>
    /// Interaction logic for HomePage.xaml
    /// </summary>
    public partial class HomePage : UserControl
    {
        public HomePage()
        {
            InitializeComponent();
            Task<HttpStatusCode> taskDepartmentStatistics;
            taskDepartmentStatistics = Task.Run(() => HTTPClient.GetDepartmentStatisticsAsync());
            Task<HttpStatusCode> t = Task.Run(() => HTTPClient.LoadActiveOperation());
            taskDepartmentStatistics.Wait();
            lblDepartmentName.Content = HTTPClient.Department.Name;
            lblNumMembers.Content = HTTPClient.DepartmentStats.NumberOfMembers;
            lblNumMembersOperation.Content = HTTPClient.DepartmentStats.NumberOfMembersInOperations;
            lblNumOperations.Content = HTTPClient.DepartmentStats.NumberOfOperations;
            t.Wait();
            btnReady.IsEnabled = (HTTPClient.ActiveOperation != null);
        }

        private void BtnReady_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                Task<HttpStatusCode> t = Task.Run(() => HTTPClient.EndOperation());
                t.Wait();
                if (t.Result == HttpStatusCode.OK)
                    btnReady.IsEnabled = false;
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message, "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
    }
}
