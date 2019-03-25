using ELIKAD_Verwaltungsclient.Data;
using ELIKAD_Verwaltungsclient.UserControls;
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
    /// Interaction logic for FunctionsWindow.xaml
    /// </summary>
    public partial class FunctionsWindow : Window
    {
        MembersPage mp;
        List<Function> memberFunctions = new List<Function>();
        List<Function> memberAddFunctions = new List<Function>();
        List<Function> memberDeleteFunctions = new List<Function>();
        List<Function> allFunctions = new List<Function>();

        public FunctionsWindow(MembersPage mp)
        {
            InitializeComponent();
            this.mp = mp;
            setCheckBoxes();
        }

        private void ListView_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            
        }

        private void setCheckBoxes()
        {
            try
            {
                Member m = (Member)mp.dgMembers.SelectedItem;
                Task<List<Function>> functionsTask = Task.Run(() => HTTPClient.GetFunctionsAsync());
                Task<List<Function>> memberFunctionsTask = Task.Run(() => HTTPClient.GetFunctionsByMemberAysnc(m));
                CheckBox cb = null;

                functionsTask.Wait();
                allFunctions = functionsTask.Result;
                memberFunctionsTask.Wait();
                memberFunctions = memberFunctionsTask.Result;

                foreach (Function function in allFunctions)
                {
                    cb = new CheckBox();
                    cb.Name = "cb" + function.Name;
                    cb.Content = function.Name;
                    cb.IsChecked = memberFunctions.Contains(function);
                    cb.Checked += checkBoxes_Checked;
                    cb.Unchecked += checkBoxes_Unchecked;
                    listViewFunctions.Items.Add(cb);
                }
            }
            catch (Exception)
            {
                resetAndClose();
            }
        }

        private void BtnDeligate_Click(object sender, RoutedEventArgs e)
        {
            try {
                List<Task> colTasks = new List<Task>();
                foreach(Function f in memberAddFunctions)
                    colTasks.Add(HTTPClient.AddFunctionsAsync((Member)mp.dgMembers.SelectedItem, f));
                foreach (Function f in memberDeleteFunctions)
                    colTasks.Add(HTTPClient.DeleteFunctionsAsync((Member)mp.dgMembers.SelectedItem, f));
            }
            finally
            {
                resetAndClose();
            }
        }

        private void checkBoxes_Checked(object sender, RoutedEventArgs e)
        {
            int idx = listViewFunctions.Items.IndexOf((CheckBox)e.OriginalSource);

            memberDeleteFunctions.Remove(allFunctions[idx]);
            memberAddFunctions.Add(allFunctions[idx]);
        }

        private void checkBoxes_Unchecked(object sender, RoutedEventArgs e)
        {
            int idx = listViewFunctions.Items.IndexOf((CheckBox)e.OriginalSource);

            memberDeleteFunctions.Add(allFunctions[idx]);
            memberAddFunctions.Remove(allFunctions[idx]);
        }

        private void BtnCancel_Click(object sender, RoutedEventArgs e)
        {
            resetAndClose();
        }

        private void resetAndClose()
        {
            memberFunctions.Clear();
            memberAddFunctions.Clear();
            memberDeleteFunctions.Clear();
            allFunctions.Clear();

            this.Close();
        }
    }
}
