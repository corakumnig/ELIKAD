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
    /// Interaction logic for AddMember.xaml
    /// </summary>
    public partial class AddMember : Window
    {
        public AddMember()
        {
            InitializeComponent();
        }

        private void btnAddMember_Click(object sender, RoutedEventArgs e)
        {

        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        public class TextInputToVisibilityConverter : IMultiValueConverter
        {
            public object Convert(object[] values, Type targetType, object parameter, System.Globalization.CultureInfo culture)
            {
                // Always test MultiValueConverter inputs for non-null 
                // (to avoid crash bugs for views in the designer) 
                if (values[0] is bool && values[1] is bool)
                {
                    bool hasText = !(bool)values[0];
                    bool hasFocus = (bool)values[1];
                    if (hasFocus || hasText)
                        return Visibility.Collapsed;
                }
                return Visibility.Visible;
            }
            public object[] ConvertBack(object value, Type[] targetTypes, object parameter, System.Globalization.CultureInfo culture)
            {
                throw new NotImplementedException();
            }
        }
    }
}
