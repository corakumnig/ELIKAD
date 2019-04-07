using ELIKAD_Verwaltungsclient.Data;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Text.RegularExpressions;
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
    /// Interaction logic for ReportWindow.xaml
    /// </summary>
    public partial class ReportWindow : Window
    {
        Operation ops;
        Report rep;
        public ReportWindow(Operation ops)
        {
            InitializeComponent();
            this.ops = ops;
            lblInfo.Content = ops.Type + ": " + ops.StartDatetime.ToShortDateString().Replace("\\", ".");
            Task<Report> t = Task.Run(() => HTTPClient.GetReport(ops));
            t.Wait();
            rep = t.Result;
            if (rep == null)
                rep = new Report();
            else
            {
                txtInjured.Text = rep.OperationInjured.ToString();
                txtKilled.Text = rep.OperationKilled.ToString();
                txtObject.Text = rep.OperationObject;
                txtOthers.Text = rep.OperationOther;
                txtReason.Text = rep.OperationReason;
                txtTime.Text = rep.OperationTime;
            }
        }

        private void BtnCancel_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void BtnSend_Click(object sender, RoutedEventArgs e)
        {
            Regex regex = new Regex("[0-9]{2}:[0-9]{2}");
            rep.OperationId = ops.Id;
            rep.OperationDepartment = HTTPClient.Department.Id;
            try
            {
                rep.OperationInjured = int.Parse(txtInjured.Text);
                rep.OperationKilled = int.Parse(txtKilled.Text);
            }
            catch (Exception)
            {
                MessageBox.Show("Tote und Verletze müssen eine Zahl sein!", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            rep.OperationObject = txtObject.Text;
            rep.OperationOther = txtOthers.Text;
            rep.OperationReason = txtReason.Text;
            if(regex.IsMatch(txtTime.Text))
                rep.OperationTime = txtTime.Text;
            else
                MessageBox.Show("Zeit muss in diesem Format eingegeben werden: (hh:mm)", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            Task<HttpStatusCode> t = Task.Run(() => HTTPClient.PostReport(rep));
            t.Wait();
            if(t.Result == HttpStatusCode.OK)
            {
                this.Close();
            }
        }
    }
}
