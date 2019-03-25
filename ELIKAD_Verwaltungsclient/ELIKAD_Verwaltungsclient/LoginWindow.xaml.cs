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
using System.Windows.Shapes;

namespace ELIKAD_Verwaltungsclient
{
    /// <summary>
    /// Interaction logic for LoginWindow.xaml
    /// </summary>
    public partial class LoginWindow : Window
    {
        public LoginWindow()
        {
            InitializeComponent();
            HTTPClient.Init();
            try
            {
                if (HTTPClient.Token != null && !HTTPClient.Token.Equals(""))
                {
                    btnLogin_Click(null, null);
                }
            }
            catch (Exception ex)
            {
                lblMessage.Content = ex.Message;
            }
        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            Application.Current.Shutdown();
        }

        private void btnLogin_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                string username = txtUsername.Text;
                string password = txtPassword.Password;
                Task<HttpStatusCode> task = Task.Run(() => HTTPClient.LoginAsync(new LoginCredentials(username, password)));
                task.Wait();
                HttpStatusCode statusCode = task.Result;

                if (statusCode == HttpStatusCode.OK)
                {
                    loadData();
                    new MainWindow().Show();
                    this.Close();
                }
                else
                    lblMessage.Content = "Falscher Username oder Passwort";
            }
            catch(Exception ex)
            {
                lblMessage.Content = "Keine Verbindung!";
            }
        }

        private void loadData()
        {
            try
            {
                Task<HttpStatusCode> taskDepartment;
                Task<HttpStatusCode> taskDepartmentStatistics;

                taskDepartment = Task.Run(() => HTTPClient.GetDepartmentAsync());
                taskDepartment.Wait();

                taskDepartmentStatistics = Task.Run(() => HTTPClient.GetDepartmentStatisticsAsync());
                taskDepartmentStatistics.Wait();
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message, "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
    }
}
