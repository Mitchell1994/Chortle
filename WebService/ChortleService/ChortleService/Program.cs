using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;
using System.Net;
using System.ServiceModel.Web;
using System.ServiceModel;
using System.IO;
using System.Diagnostics;
using System.Collections.Specialized;
using System.Web;
using System.Runtime.Serialization;
using Newtonsoft.Json.Linq;

namespace ChortleService
{
    [DataContract]
    public class User
    {
        [DataMember]
        public string username { get; set; }
        [DataMember]
        public string firstname { get; set; }
        [DataMember]
        public string lastname { get; set; }
        [DataMember]
        public string email { get; set; }
        [DataMember]
        public string hash { get; set; }
    }


    [ServiceContract]
    public partial class ChortleService
    {
        //UserTableAdapter uta = new UserTableAdapter();

        [WebInvoke(Method = "POST", UriTemplate = "users", BodyStyle = WebMessageBodyStyle.Wrapped, RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        void addUser(Stream body)
        {
            Console.Write("Recieved");
            StreamReader reader = new StreamReader(body);
            string res = reader.ReadToEnd();
            reader.Close();
            reader.Dispose();
            JObject user = JObject.Parse(res);
            //uta.Insert((String)user["username"], (String)user["firstname"], (String)user["lastname"], (String)user["email"], (String)user["hash"]);
        }

        [WebGet(UriTemplate = "users")]
        [OperationContract]
        void getUsers()
        {
            Console.Write("Getting");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            WebServiceHost host = new WebServiceHost(typeof(ChortleService), new Uri("http://localhost:8080/chortleservice"));
            host.Open();
            while (true) ;
        }
    }
}
