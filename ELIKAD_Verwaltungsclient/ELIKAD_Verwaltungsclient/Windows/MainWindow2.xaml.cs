using ELIKAD_Verwaltungsclient.Data;
using ELIKAD_Verwaltungsclient.UserControls;
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
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace ELIKAD_Verwaltungsclient.Windows
{
    /// <summary>
    /// </summary>    /// Interaction logic for MainWindow.xaml

    public partial class MainWindow : Window
    {
        private bool menuStatus = true;
        private string currentView = "somewhere";
        private string currentSliderState = "closed";

        public MainWindow()
        {
            InitializeComponent();
            try
            {
                this.MaxHeight = SystemParameters.MaximizedPrimaryScreenHeight;
                lblAdminName.Content = "Hallo, " + HTTPClient.Member.Firstname + " " + HTTPClient.Member.Lastname;
                BtnHome_Click(null, null);
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message, "Fehler", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void btnLeftMenu_Click(object sender, RoutedEventArgs e)
        {
            showHideMenu();
            if(currentSliderState == "closed")
            {
                imgSlider.Source = new BitmapImage(new Uri(@"..\Pics\slider_left.png", UriKind.Relative));
                currentSliderState = "opened";
            }
            else if (currentSliderState == "opened")
            {
                imgSlider.Source = new BitmapImage(new Uri(@"..\Pics\slider_right.png", UriKind.Relative));
                currentSliderState = "closed";
            }
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

        private void BtnHome_Click(object sender, RoutedEventArgs e)
        {
            if (currentView != "home")
            {
                HomePage dept = new HomePage();
                gridUserControl.Children.Clear();
                gridUserControl.Children.Add(dept);
                currentView = "home";
            }
            showHideMenu();
        }

        private void BtnLogout_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                Properties.Settings.Default.BearerToken = "";
                Properties.Settings.Default.Save();
                System.Windows.Application.Current.Shutdown();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Fehler", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
    }
}
