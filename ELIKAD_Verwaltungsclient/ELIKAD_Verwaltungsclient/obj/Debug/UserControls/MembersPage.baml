   M S B A M L   `   `   `  ����S  OELIKAD_Verwaltungsclient, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null]3clr-namespace:ELIKAD_Verwaltungsclient.UserControls%ELIKAD_Verwaltungsclient.UserControls  5      7    1ELIKAD_Verwaltungsclient.UserControls.MembersPage   5      W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5      R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      @d2http://schemas.microsoft.com/expression/blend/2008     5      Elocal3clr-namespace:ELIKAD_Verwaltungsclient.UserControls     5      $��Stretch=�5      $��Stretch=�5      $��Stretch=�5	      $��Stretch=�5
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
��1500��6   $
��auto �6/   6>   �� 5*      $	��659��6   $
��auto �6.   .+�5,   
   &   System.Windows.Controls.DataGrid  -   5          Name$ 	dgMembers��5,         
IsReadOnly . $   HorizontalScrollBarVisibility$ Visible=�6i   "   VerticalScrollBarVisibility$ Visible=�6�      AutoGenerateColumns .  $��0��6�   $	��100��6�   $
��1000��6�   $
��auto��6  $
��auto��6  $��15,10,10,10q�6  $��18(�6b  ��6Q   ��    Columns 5-      0  *System.Windows.Controls.DataGridTextColumn  5.      ,  &System.Windows.Controls.DataGridColumn   MinWidth$ 50Q�6%      Header$ Id��63   1  +System.Windows.Controls.DataGridBoundColumn   Binding 6?   �� *Id+  5/      $	 120Q�6%   $ SV-Nr��64    6C   �� *SvNr+  50      $	 200Q�6%   $ Vorname��64    6E   �� *	Firstname+  51      $	 200Q�6%   $ Nachname��64    6F   �� *
Lastname+  52      $	 180Q�6%   $ Geburtsdatum��64    6J   �� *DateOfBirth+ �� StringFormat$ {0:dd.MM.yyyy}��  53      $	 180Q�6%   $ Eintrittsdatum��64    6L   �� *DateOfEntry+$ {0:dd.MM.yyyy}��  54      $	 200Q�6%   $ Telefonnummer��64    6K   �� *Phonenumber+  55      $	 230Q�6%   $ E-mail��64    6D   �� *Email+  56      $	 100Q�6%   $ 
Geschlecht��64    6H   �� *Gender+58      �� 59   
   -   5         ��Name$ 	txtSearch��59      $��
0,35,425,0q�6%    �� TextWrapping$
 Wrap=�69   $�� ��6M   $��1��6U   $��Right=�6e   $	��Top=�6�   $��27��6�   $	��205��6�   $��18(�6�   �� 5:   
   -   5         ��Name$ 	cmbFilter��5:      $��
0,35,305,0q�6&   $��1��6:   $��Right=�6J   $	��Top=�6f   $��27��6~   $	��120��6�   $��18(�6�   �� 5;   
   -   5         ��Name$ btnAddNewMember��5;      $��Neues Mitglied��6*   $��1��6C   $
��Left=�6S   $��107,102,0,-28q�6n   $	��Top=�6�   $	��221��6�   $��46��6�   $��18(�6�   �� 5<   
   -   5        $ btnDeleteMember��5<      $��Löschen��6*   $��1��6<   $
��Left=�6L   $��107,171,0,-85q�6g   $	��Top=�6~   $	��221��6�   $��46��6�   $��18(�6�   �� 5=   
   -   5        $ btnEdit��5=      $��Ändern��6"   $��1��63   $
��Left=�6C   $��106,240,0,-154q�6^   $	��Top=�6v   $	��221��6�    J� RenderTransformOrigin$ 
0.318,1.63&�6�   $��46��6�   $��18(�6�   �� 5>   
   -   5        $ btnAddFunction��5>      $��
Funktionen��6)   $��1��6>   $	��Top=�6N   $
��Left=�6f   $��106,307,0,-221q�6�   $	��221��6�   $��46��6�   $��18(�6�   �� 5?   
   -   5        $ btnAddExistingMember��5?      $��Mitglied übernehmen��6/   $��1��6M   $	��Top=�6]   $
��Left=�6u   $��106,373,0,-285q�6�   $	��221��6�   $��46��6�   $��18(�6�   5@      5A      