using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ELIKAD_Verwaltungsclient.Data
{
    public class Report
    {
        public int OperationId { get; set; }
        public int OperationInjured { get; set; }
        public int OperationKilled { get; set; }
        public string OperationObject { get; set; }
        public string OperationOther { get; set; }
        public string OperationReason { get; set; }
        public string OperationTime { get; set; }
        public int OperationDepartment { get; set; }

        public Report(int operationId, int operationInjured, int operationKilled, string operationObject, string operationOther, string operationReason, string operationTime, int operationDepartment)
        {
            OperationId = operationId;
            OperationInjured = operationInjured;
            OperationKilled = operationKilled;
            OperationObject = operationObject;
            OperationOther = operationOther;
            OperationReason = operationReason;
            OperationTime = operationTime;
            OperationDepartment = operationDepartment;
        }

        public Report() { }
    }
}
