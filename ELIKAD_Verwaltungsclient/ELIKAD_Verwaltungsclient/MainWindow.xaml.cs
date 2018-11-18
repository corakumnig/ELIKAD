using ELIKAD_Verwaltungsclient.Data;
using ELIKAD_Verwaltungsclient.UserControls;
using ELIKAD_Verwaltungsclient.Windows;
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
        public string currentView = "home";

        public MainWindow()
        {
            InitializeComponent();
            this.MaxHeight = SystemParameters.MaximizedPrimaryScreenHeight;
            HTTPClient.Init();
            this.Visibility = Visibility.Hidden;
            new LoginWindow(this).Visibility = Visibility.Visible;
        }

        private void btnLeftMenu_Click(object sender, RoutedEventArgs e)
        {
            showHideMenu();
        }

        private void showHideMenu()
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

        private void btnMembers_Click(object sender, RoutedEventArgs e)
        {
            if (currentView != "members")
            {
                MembersPage mems = new MembersPage(this);
                gridUserControl.Children.Clear();
                gridUserControl.Children.Add(mems);
                currentView = "members";
            }
            showHideMenu();
        }

        private void btnDepartment_Click(object sender, RoutedEventArgs e)
        {
            if (currentView != "department")
            {
                DepartmentPage dept = new DepartmentPage(this);
                gridUserControl.Children.Clear();
                gridUserControl.Children.Add(dept);
                currentView = "department";
            }
            showHideMenu();
        }
    }
}
