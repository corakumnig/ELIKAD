   M S B A M L   `   `   `  ����S  OELIKAD_Verwaltungsclient, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullC&clr-namespace:ELIKAD_VerwaltungsclientELIKAD_Verwaltungsclient  5   	   *    $ELIKAD_Verwaltungsclient.LoginWindow   5   	   W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   8local&clr-namespace:ELIKAD_Verwaltungsclient     5   	     � Title$  Login��5   	   $��221.276��6   $��330.322��6(    � 
ResizeMode$ NoResize=�5	   	   .��5
      � .+�5   
   �� $��	Username:��6   $
��Left=�6$   $��	35,13,0,0q�6?   $	��Top=�6R   $��31��6j   $��87��6v   �� 5   
   $��	Password:��6   $
��Left=�6$   $��	35,51,0,0q�6?   $	��Top=�6R   $��25��6j   $��87��6v   �� 5   
   -   5         ��Name$ txtUsername��5      $
��Left=�6'   $��33��6B   $��
130,13,0,0q�6N    �� TextWrapping$
 Wrap=�6b   $��Center=�6v   $��15(�6�   $��kraschlc��6�   $	��Top=�6�   $	��172��6�   G� 5   
   -   5         G�Name$ txtPassword��5       G� Password$ Test123��6+   $��Center=�6>   $��15(�6`   $
��Left=�6o   $��33��6�   $��
130,51,0,0q�6�   $	��Top=�6�   $	��172��6�   �� 5   
   -   5         ��Name$ btnLogin��5      $��Login��6#   $
��Left=�63   $��201,117,0,0q�6N   $	��Top=�6c   $	��101��6{   $��27��6�   6�   �� 5   
   -   5        $ 	btnCancel��5      $��Cancel��6$   $
��Left=�65   $��
35,117,0,0q�6P   $	��Top=�6d   $	��101��6|   $��27��6�   6�   �� 5   
   -   5         ��Name$ 
lblMessage��5      $��Normal$�6$   $ ��Bitte loggen Sie sich ein!��67   $
��Left=�6\   $��
35,149,0,0q�6w   $	��Top=�6�   $��26��6�   $	��267��6�   �� 5   
   -   5         ��Name$ cbStay��5      	 d� 	IsChecked$
	 TrueY�6#   $��Eingeloggt bleiben?��64   $
��Left=�6R   $��	41,94,0,0q�6m   $	��Top=�6�   $��14(�6�   5      5      