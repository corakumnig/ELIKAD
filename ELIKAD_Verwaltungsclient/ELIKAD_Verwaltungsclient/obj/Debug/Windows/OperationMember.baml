   M S B A M L   `   `   `  ����S  OELIKAD_Verwaltungsclient, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullS.clr-namespace:ELIKAD_Verwaltungsclient.Windows ELIKAD_Verwaltungsclient.Windows  5   	   6    0ELIKAD_Verwaltungsclient.Windows.OperationMember   5   	   W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   @local.clr-namespace:ELIKAD_Verwaltungsclient.Windows     5   	     � Title$  
Mitglieder��5   	   $��836.184��6   $��752.707��6-   .��5	      � $��0,0,0,1q�6    � ColumnDefinitions 5
   
   �� 5      $	��400��6   $
��auto �6.   6=   .+�5   
   &   System.Windows.Controls.DataGrid  -   5          Name$ dgMembersWasntThere��5         
IsReadOnly . $   HorizontalScrollBarVisibility$ Visible=�6s   "   VerticalScrollBarVisibility$ Visible=�6�      AutoGenerateColumns .  $��0��6�   $	��100��6�   $	��400��6�   $
��auto��6  $��16,461,317,44q�6  ��6[   ��    Columns 5      0  *System.Windows.Controls.DataGridTextColumn  5      ,  &System.Windows.Controls.DataGridColumn   MinWidth$ 40Q�6%   	   Header$	 Id��63   1  +System.Windows.Controls.DataGridBoundColumn
   Binding
 6?   �� *Id+  5      $ 90Q�6%   $	 SV-Nr��63   
 6B   �� *SvNr+  5      $	 150Q�6%   $	 Vorname��64   
 6E   �� *	Firstname+  5      $	 150Q�6%   $	 Nachname��64   
 6F   �� *
Lastname+  5      $	 130Q�6%   $	 Geburtsdatum��64   
 6J   �� *DateOfBirth+ �� StringFormat$ {0:dd.MM.yyyy}��  5      $ 60Q�6%   $	 
Geschlecht��63   
 6G   �� *Gender+4  .System.Windows.Controls.DataGridTemplateColumn  5      $ 60Q�6)   $	 Aktion��67      CellTemplate 5      �� 5      .R�5      ��  ��Name$ btnAdd��6%   -   5        $��Hinzufügen��5   5   6J   5      5      5      �� 5   
   -   5         ��Name$ txtSearchWasntThere��5      $��0,429,666,0q�6]    �� TextWrapping$
 Wrap=�6r   $�� ��6�   $��Right=�6�   $	��Top=�6�   $��27��6�   $	��205��6�   �� 5   
   -   5         ��Name$ cmbFilterWasntThere��5      $��0,429,900,0q�60   $��Right=�6E   $	��Top=�6a   $��27��6y   $	��120��6�   �� 5    
   -   5        $ 	btnCancel��5       $��	Abbrechen��6$   $
��Left=�68   $��
16,766,0,0q�6S   $	��Top=�6g   $	��132��6   $��29��6�   6�     5!   
   -   5        $ dgMembersWasThere��5!       . $ Visible=�6q   $ Visible=�6�    .  $��0��6�   $	��100��6�   $	��400��6�   $
��auto��6  $��16,76,317,429q�6  ��6Y   ��  5"        5#      $ 40Q�6%   $	 Id��63   
 6?   �� *Id+  5$      $ 90Q�6%   $	 SV-Nr��63   
 6B   �� *SvNr+  5%      $	 150Q�6%   $	 Vorname��64   
 6E   �� *	Firstname+  5&      $	 150Q�6%   $	 Nachname��64   
 6F   �� *
Lastname+  5'      $	 130Q�6%   $	 Geburtsdatum��64   
 6J   �� *DateOfBirth+$ {0:dd.MM.yyyy}��  5(      $ 60Q�6%   $	 
Geschlecht��63   
 6G   �� *Gender+  5)      $ 60Q�6)   $	 Aktion��67    5*      �� 5+      .R�5,      �� $ 	btnDelete��6%   -   5        $��	Entfernen��5,   8   6L   5-      5/      51      �� 52   
   -   5        $ txtSearchWasThere��52      $��
0,44,666,0q�6Y   $
 Wrap=�6m   $�� ��6�   $��Right=�6�   $	��Top=�6�   $��27��6�   $	��205��6�   �� 53   
   -	   5        $ cmbFilterWasThere��53      $��
0,44,900,0q�6.   $��Right=�6B   $	��Top=�6^   $��27��6v   $	��120��6�   �� 54   
   $��Waren dabei��6   $��19(�6&   $
��Left=�64   $��	16,10,0,0q�6O   $	��Top=�6b   $	��143��6z   �� 55   
   $��Waren nicht dabei��6   $��19(�6,   $
��Left=�6:   $��
20,389,0,0q�6U   $	��Top=�6i   $	��187��6�   56      57      