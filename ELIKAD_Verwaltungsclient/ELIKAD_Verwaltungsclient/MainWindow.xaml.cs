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
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace ELIKAD_Verwaltungsclient
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private bool menuStatus = false;
        public MainWindow()
        {
            InitializeComponent();
        }

        private void btnLeftMenu_Click(object sender, RoutedEventArgs e)
        {
            ShowHideMenu();
        }

        private void ShowHideMenu()
        {
            if (menuStatus)
            {
                Storyboard sb = Resources["sbHideLeftMenu"] as Storyboard;
                sb.Begin(pnlLeftMenu);
                menuStatus = false;
            }
            else
            {
                Storyboard sb = Resources["sbShowLeftMenu"] as Storyboard;
                sb.Begin(pnlLeftMenu);
                menuStatus = true;
            }
        }
    }
}
