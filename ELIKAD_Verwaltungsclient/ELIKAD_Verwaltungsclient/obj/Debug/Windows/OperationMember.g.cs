﻿#pragma checksum "..\..\..\Windows\OperationMember.xaml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "985F6547C909426FFF371322BD25BAA0C78AF61E"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using ELIKAD_Verwaltungsclient.Windows;
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


namespace ELIKAD_Verwaltungsclient.Windows {
    
    
    /// <summary>
    /// OperationMember
    /// </summary>
    public partial class OperationMember : System.Windows.Window, System.Windows.Markup.IComponentConnector, System.Windows.Markup.IStyleConnector {
        
        /// <summary>
        /// dgMembersWasntThere Name Field
        /// </summary>
        
        #line 13 "..\..\..\Windows\OperationMember.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        public System.Windows.Controls.DataGrid dgMembersWasntThere;
        
        #line default
        #line hidden
        
        
        #line 30 "..\..\..\Windows\OperationMember.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox txtSearchWasntThere;
        
        #line default
        #line hidden
        
        
        #line 31 "..\..\..\Windows\OperationMember.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.ComboBox cmbFilterWasntThere;
        
        #line default
        #line hidden
        
        
        #line 32 "..\..\..\Windows\OperationMember.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button btnCancel;
        
        #line default
        #line hidden
        
        /// <summary>
        /// dgMembersWasThere Name Field
        /// </summary>
        
        #line 33 "..\..\..\Windows\OperationMember.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        public System.Windows.Controls.DataGrid dgMembersWasThere;
        
        #line default
        #line hidden
        
        
        #line 50 "..\..\..\Windows\OperationMember.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox txtSearchWasThere;
        
        #line default
        #line hidden
        
        
        #line 51 "..\..\..\Windows\OperationMember.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.ComboBox cmbFilterWasThere;
        
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
            System.Uri resourceLocater = new System.Uri("/ELIKAD_Verwaltungsclient;component/windows/operationmember.xaml", System.UriKind.Relative);
            
            #line 1 "..\..\..\Windows\OperationMember.xaml"
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
            this.dgMembersWasntThere = ((System.Windows.Controls.DataGrid)(target));
            
            #line 13 "..\..\..\Windows\OperationMember.xaml"
            this.dgMembersWasntThere.SelectionChanged += new System.Windows.Controls.SelectionChangedEventHandler(this.DgMembers_SelectionChanged);
            
            #line default
            #line hidden
            return;
            case 3:
            this.txtSearchWasntThere = ((System.Windows.Controls.TextBox)(target));
            
            #line 30 "..\..\..\Windows\OperationMember.xaml"
            this.txtSearchWasntThere.TextChanged += new System.Windows.Controls.TextChangedEventHandler(this.txtSearchWasntThere_TextChanged);
            
            #line default
            #line hidden
            return;
            case 4:
            this.cmbFilterWasntThere = ((System.Windows.Controls.ComboBox)(target));
            return;
            case 5:
            this.btnCancel = ((System.Windows.Controls.Button)(target));
            
            #line 32 "..\..\..\Windows\OperationMember.xaml"
            this.btnCancel.Click += new System.Windows.RoutedEventHandler(this.BtnCancel_Click);
            
            #line default
            #line hidden
            return;
            case 6:
            this.dgMembersWasThere = ((System.Windows.Controls.DataGrid)(target));
            
            #line 33 "..\..\..\Windows\OperationMember.xaml"
            this.dgMembersWasThere.SelectionChanged += new System.Windows.Controls.SelectionChangedEventHandler(this.DgMembers_SelectionChanged);
            
            #line default
            #line hidden
            return;
            case 8:
            this.txtSearchWasThere = ((System.Windows.Controls.TextBox)(target));
            
            #line 50 "..\..\..\Windows\OperationMember.xaml"
            this.txtSearchWasThere.TextChanged += new System.Windows.Controls.TextChangedEventHandler(this.txtSearchWasThere_TextChanged);
            
            #line default
            #line hidden
            return;
            case 9:
            this.cmbFilterWasThere = ((System.Windows.Controls.ComboBox)(target));
            return;
            }
            this._contentLoaded = true;
        }
        
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Never)]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Design", "CA1033:InterfaceMethodsShouldBeCallableByChildTypes")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1800:DoNotCastUnnecessarily")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Maintainability", "CA1502:AvoidExcessiveComplexity")]
        void System.Windows.Markup.IStyleConnector.Connect(int connectionId, object target) {
            switch (connectionId)
            {
            case 2:
            
            #line 24 "..\..\..\Windows\OperationMember.xaml"
            ((System.Windows.Controls.Button)(target)).Click += new System.Windows.RoutedEventHandler(this.BtnAdd_Click);
            
            #line default
            #line hidden
            break;
            case 7:
            
            #line 44 "..\..\..\Windows\OperationMember.xaml"
            ((System.Windows.Controls.Button)(target)).Click += new System.Windows.RoutedEventHandler(this.BtnDelete_Click);
            
            #line default
            #line hidden
            break;
            }
        }
    }
}
