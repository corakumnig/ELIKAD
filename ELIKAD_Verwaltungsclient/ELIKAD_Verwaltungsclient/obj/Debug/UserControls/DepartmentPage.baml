   M S B A M L   `   `   `  ����S  OELIKAD_Verwaltungsclient, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null]3clr-namespace:ELIKAD_Verwaltungsclient.UserControls%ELIKAD_Verwaltungsclient.UserControls  5      :    4ELIKAD_Verwaltungsclient.UserControls.DepartmentPage   5      W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5      R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      @d2http://schemas.microsoft.com/expression/blend/2008     5      Elocal3clr-namespace:ELIKAD_Verwaltungsclient.UserControls     5      $��Stretch=�5      $��Stretch=�5      $��Stretch=�5	      $��Stretch=�5
      $
��auto��5      $
��auto��5        � 	Resources  5         MyButtonStyle �� 
TargetType �� Property �� Value �� 
TargetType �� Padding �� CornerRadius �� Triggers P� Property	 P� Value
 �� 
TargetName��%+  &	        �� 5   
   " ��6&   .�5      ��  �@�OverridesDefaultStyle . 66   �� 5       � ��$ 5q�6'   �� 5       � �� �#FFFFFF6+   �� 5       � �� 5      �� 5      " ��6&   .R�5      �� $��border��6!   $��1q�5      $	 4,2q�5      
����iA�$ 3��5      #��z��5      ._�5      �� $��Center=�6/   $��Center=�6L   5       5      P� 5       �@J�IsMouseOver	 . . �5   "   �� $
 border��6)    � ��
 �   �6T   5       5"      5$      5%      5&      .��5'      �  � ColumnDefinitions 5(   
   �� 5)      $
��1000��6   $
��auto �6/   6>   �� 5*      $	��200��6   $
��auto �6.   .+�5,   
   &   System.Windows.Controls.DataGrid  -   5          Name$ dgOperations��5,         AutoGenerateColumns .     
IsReadOnly . $   HorizontalScrollBarVisibility$ Visible=�6�   "   VerticalScrollBarVisibility$ Visible=�6�   $��0��6�   $	��100��6�   $
��1000��6  $
��auto��6  $
��auto��6*  $��15,10,10,10q�67  $��16(�6L  ��6�   ��    Columns 5-      0  *System.Windows.Controls.DataGridTextColumn  5.      ,  &System.Windows.Controls.DataGridColumn   MinWidth$ 40Q�6%      Header$ Id��63   1  +System.Windows.Controls.DataGridBoundColumn   Binding 6?   �� *Id+  5/      $	 130Q�6%   $ 	Alarmzeit��64    6G   �� *StartDatetime+ �� StringFormat$ {0:dd.MM.yyyy hh:mm}��  50      $	 130Q�6%   $ Einrückzeit��64    6I   �� *EndDatetime+$ {0:dd.MM.yyyy hh:mm}��  51      $	 130Q�6%   $ 
Einsatzart��64    6H   �� *Type+4  .System.Windows.Controls.DataGridTemplateColumn  52      $	 275Q�6)   $
 Text��68      CellTemplate 53      �� 54      .R�55      �� $��WrapWithOverflow=�6>   ��6(   �� *Text+6>   56      58        59      $	 130Q�6%   $ 
Alarmstufe��64    6H   �� *
Alarmlevel+  5:      $	 150Q�6%   $ 
Leitstelle��64      Width5  /System.Windows.Controls.DataGridLengthConverter$ * 6n    6H   �� *ControlcenterName+6n   5<      �� 5=   
   -   5         ��Name$ 	cmbFilter��5=      $��
0,43,363,0q�6T   $��1��6h   $��Right=�6x   $	��Top=�6�   $��34��6�   $	��127��6�   $��16(�6�   �� 5>   
   -   5         ��Name$ 	btnReport��5>      $��Bericht��6$   $��1��66   $
��Left=�6F   $��
320,43,0,0q�6a   $	��Top=�6u   $	��127��6�    J� RenderTransformOrigin$ 
0.318,1.63&�6�   $��34��6�   $��16(�6�   �� 5?   
   -   5        $ btnAddMember��5?      $��
Mitglieder��6'   $��1��6<   $
��Left=�6L   $��
161,43,0,0q�6g   $	��Top=�6{   $��34��6�   $	��127��6�   $��16(�6�   �� 5@   
   $��Straße:��6   $��1��6"   $
��Left=�62   $��142,153,0,0q�6M   $	��Top=�6b   $��20(�6z   �� 5A   
   $��Mitgliederanzahl:��6   $��1��6,   $
��Left=�6<   $��42,300,0,-94q�6W   $	��Top=�6m   $��20(�6�   �� 5B   
   $
��Ort:��6   $��1��6   $
��Left=�6/   $��161,260,0,-54q�6J   $	��Top=�6a   $��20(�6y   �� 5C   
   $
��PLZ:��6   $��1��6   $
��Left=�6/   $��161,223,0,-17q�6J   $��20(�6a   �� 5D   
   $��Hausnummer:��6   $��1��6&   $
��Left=�66   $��
79,186,0,0q�6Q   $	��Top=�6e   $��20(�6}   �� 5E   
   -   5         ��Name$ 	lblStreet��5E      $�� ��6#   $��1��6.   $
��Left=�6>   $��231,153,0,0q�6Y   $	��Top=�6n   $��20(�6�   �� 5F   
   -   5        $ lblHousenum��5F      $�� ��6%   $��1��60   $
��Left=�6@   $��231,186,0,0q�6[   $	��Top=�6p   $ 0.497,-0.531&�6�   $��20(�6�   �� 5G   
   -   5        $ 	lblPostal��5G      $�� ��6#   $��1��6.   $
��Left=�6>   $��231,223,0,-17q�6Y   $	��Top=�6p   $��20(�6�   �� 5H   
   -   5        $ lblCity��5H      $�� ��6!   $��1��6,   $
��Left=�6<   $��231,263,0,-57q�6W   $	��Top=�6n   $��20(�6�   �� 5I   
   -	   5        $ lblMembersNum��5I      $�� ��6'   $��1��62   $
��Left=�6B   $��231,300,0,-94q�6]   $��20(�6t   5K      5L      