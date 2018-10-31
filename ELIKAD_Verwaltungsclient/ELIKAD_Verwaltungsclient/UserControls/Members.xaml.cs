using ELIKAD_Verwaltungsclient.Data;
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
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace ELIKAD_Verwaltungsclient.UserControls
{
    /// <summary>
    /// Interaction logic for Members.xaml
    /// </summary>
    public partial class Members : UserControl
    {
        List<Member> listMembers = new List<Member>();
        private MainWindow mainWindow;

        public Members(MainWindow mainWindow)
        {
            InitializeComponent();
            this.mainWindow = mainWindow;
            listMembers.Add(new Member(1, "Christof", "Kraschl", new DateTime(1999, 9, 3), new DateTime(2015, 8, 15), "0676/5372244", "christof@kraschl.eu", "4437030999"));
            listMembers.Add(new Member(2, "Cora", "Kumnig", new DateTime(2000, 3, 20), new DateTime(2016, 5, 2), "0676/8256472", "corakumnig@gmail.com", "4628200300"));
            dgMenbers.ItemsSource = listMembers;
        }

        private void txtSearch_TextChanged(object sender, TextChangedEventArgs e)
        {
            
        }

        private void btnAddNewMember_Click(object sender, RoutedEventArgs e)
        {
            AddMember addMemberWindow = new AddMember();
            addMemberWindow.Show();
        }
    }
}
