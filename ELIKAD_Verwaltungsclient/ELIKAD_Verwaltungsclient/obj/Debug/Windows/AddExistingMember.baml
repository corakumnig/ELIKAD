   M S B A M L   `   `   `  ����S  OELIKAD_Verwaltungsclient, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullS.clr-namespace:ELIKAD_Verwaltungsclient.Windows ELIKAD_Verwaltungsclient.Windows  5   	   8    2ELIKAD_Verwaltungsclient.Windows.AddExistingMember   5   	   W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   @local.clr-namespace:ELIKAD_Verwaltungsclient.Windows     5   	   -   5          � Title$  Mitglied hinzufügen��5   	   $��428.972��6%   $	��800��66   .��5	      � .+�5
   
   �� -   5         ��Name$ txtSearchMember��5
      $��
0,25,446,0q�6U    �� TextWrapping$
 Wrap=�6i   $�� ��6}   $��Right=�6�   $	��Top=�6�   $��27��6�   $	��205��6�   �� 5   
   -   5         ��Name$ cmbMemberFilter��5      $��
0,25,656,0q�6,   $��Right=�6@   $	��Top=�6\   $��27��6t   $	��120��6�   �� 5   
   -   5         ��Name$ 	btnCancel��5      $��	Abbrechen��6$   $
��Left=�68   $	��Top=�6S   $	��132��6k   $��29��6w   $��
16,350,0,0q�6�   &   System.Windows.Controls.DataGrid  5   
   -   5          Name$ 	dgMembers��5         
IsReadOnly . $   HorizontalScrollBarVisibility$ Visible=�6i   "   VerticalScrollBarVisibility$ Visible=�6�   	   AutoGenerateColumns	 .  $��0��6�   $	��100��6�   $	��400��6�   $
��auto��6  $��10,57,10,53q�6  ��6Q   �� 
   Columns
 5      0  *System.Windows.Controls.DataGridTextColumn  5      ,  &System.Windows.Controls.DataGridColumn   MinWidth$ 40Q�6%      Header$ Id��63   1  +System.Windows.Controls.DataGridBoundColumn   Binding 6?   �� *Id+  5      $ 90Q�6%   $ SV-Nr��63    6B   �� *SvNr+  5      $	 150Q�6%   $ Vorname��64    6E   �� *	Firstname+  5      $	 150Q�6%   $ Nachname��64    6F   �� *
Lastname+  5      $	 130Q�6%   $ Geburtsdatum��64    6J   �� *DateOfBirth+ �� StringFormat$ {0:dd.MM.yyyy}��  5      $ 60Q�6%   $ 
Geschlecht��63    6G   �� *Gender+4  .System.Windows.Controls.DataGridTemplateColumn  5      $ 60Q�6)   $ Aktion��67      CellTemplate 5      �� 5      .R�5      �� $ btnAdd��6%   -   5        $��Hinzufügen��5   5   6J   5      5      5      5      5      