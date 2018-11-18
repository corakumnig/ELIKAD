using System;
using System.Collections.Generic;
using System.Linq;
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
    /// Interaction logic for LoginWindow.xaml
    /// </summary>
    public partial class LoginWindow : Window
    {
        MainWindow mw;
        public LoginWindow(MainWindow mw)
        {
            InitializeComponent();
            this.mw = mw;
        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
            mw.Close();
        }

        private void btnLogin_Click(object sender, RoutedEventArgs e)
        {
            mw.Visibility = Visibility.Visible;
            this.Close();
        }
    }
}
