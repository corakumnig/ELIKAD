﻿#pragma checksum "..\..\..\UserControls\MembersPage2.xaml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "28703E80DD0B109BB4321D2CB6EF9AFEE18A77DD"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using ELIKAD_Verwaltungsclient.UserControls;
using System;
using System.Diagnostics;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Media.Media3D;
using System.Windows.Media.TextFormatting;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Shell;


namespace ELIKAD_Verwaltungsclient.UserControls {
    
    
    /// <summary>
    /// MembersPage
    /// </summary>
    public partial class MembersPage : System.Windows.Controls.UserControl, System.Windows.Markup.IComponentConnector {
        
        /// <summary>
        /// dgMembers Name Field
        /// </summary>
        
        #line 44 "..\..\..\UserControls\MembersPage2.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        public System.Windows.Controls.DataGrid dgMembers;
        
        #line default
        #line hidden
        
        
        #line 57 "..\..\..\UserControls\MembersPage2.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox txtSearch;
        
        #line default
        #line hidden
        
        
        #line 58 "..\..\..\UserControls\MembersPage2.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.ComboBox cmbFilter;
        
        #line default
        #line hidden
        
        
        #line 59 "..\..\..\UserControls\MembersPage2.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button btnAddNewMember;
        
        #line default
        #line hidden
        
        
        #line 60 "..\..\..\UserControls\MembersPage2.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button btnDeleteMember;
        
        #line default
        #line hidden
        
        
        #line 61 "..\..\..\UserControls\MembersPage2.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button btnEdit;
        
        #line default
        #line hidden
        
        
        #line 62 "..\..\..\UserControls\MembersPage2.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button btnAddFunction;
        
        #line default
        #line hidden
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Uri resourceLocater = new System.Uri("/ELIKAD_Verwaltungsclient;component/usercontrols/memberspage2.xaml", System.UriKind.Relative);
            
            #line 1 "..\..\..\UserControls\MembersPage2.xaml"
            System.Windows.Application.LoadComponent(this, resourceLocater);
            
            #line default
            #line hidden
        }
        
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Never)]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Design", "CA1033:InterfaceMethodsShouldBeCallableByChildTypes")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Maintainability", "CA1502:AvoidExcessiveComplexity")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1800:DoNotCastUnnecessarily")]
        void System.Windows.Markup.IComponentConnector.Connect(int connectionId, object target) {
            switch (connectionId)
            {
            case 1:
            this.dgMembers = ((System.Windows.Controls.DataGrid)(target));
            
            #line 44 "..\..\..\UserControls\MembersPage2.xaml"
            this.dgMembers.SelectionChanged += new System.Windows.Controls.SelectionChangedEventHandler(this.DgMenbers_SelectionChanged);
            
            #line default
            #line hidden
            return;
            case 2:
            this.txtSearch = ((System.Windows.Controls.TextBox)(target));
            
            #line 57 "..\..\..\UserControls\MembersPage2.xaml"
            this.txtSearch.TextChanged += new System.Windows.Controls.TextChangedEventHandler(this.txtSearch_TextChanged);
            
            #line default
            #line hidden
            return;
            case 3:
            this.cmbFilter = ((System.Windows.Controls.ComboBox)(target));
            return;
            case 4:
            this.btnAddNewMember = ((System.Windows.Controls.Button)(target));
            
            #line 59 "..\..\..\UserControls\MembersPage2.xaml"
            this.btnAddNewMember.Click += new System.Windows.RoutedEventHandler(this.btnAddNewMember_Click);
            
            #line default
            #line hidden
            return;
            case 5:
            this.btnDeleteMember = ((System.Windows.Controls.Button)(target));
            
            #line 60 "..\..\..\UserControls\MembersPage2.xaml"
            this.btnDeleteMember.Click += new System.Windows.RoutedEventHandler(this.BtnDeleteMember_Click);
            
            #line default
            #line hidden
            return;
            case 6:
            this.btnEdit = ((System.Windows.Controls.Button)(target));
            
            #line 61 "..\..\..\UserControls\MembersPage2.xaml"
            this.btnEdit.Click += new System.Windows.RoutedEventHandler(this.BtnEdit_Click);
            
            #line default
            #line hidden
            return;
            case 7:
            this.btnAddFunction = ((System.Windows.Controls.Button)(target));
            
            #line 62 "..\..\..\UserControls\MembersPage2.xaml"
            this.btnAddFunction.Click += new System.Windows.RoutedEventHandler(this.BtnAddFunction_Click);
            
            #line default
            #line hidden
            return;
            }
            this._contentLoaded = true;
        }
    }
}

