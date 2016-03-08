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
using System.Net.Http;
using System.Runtime.Serialization;
using Newtonsoft.Json.Linq;
using ChortleService.ChortleDBDataSetTableAdapters;

namespace ChortleService
{

    //GLOBAL VALUES
    public static class Values
    {
        //Used to make output more or less verbose and other things
        public static bool debugmode = true;
        public static string port = "9910";
        public static string webserviceurl = "http://localhost:" + port + "/chortleservice";
    }

    [ServiceContract]
    public partial class ChortleService
    {
        //Stores the user table as an object
        UserTableAdapter userTable = new UserTableAdapter();
        
        [WebInvoke(Method = "POST", UriTemplate = "users", BodyStyle = WebMessageBodyStyle.Wrapped, RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        void addUser(String username, String firstname, String lastname, String email, String hash)
        {
            Console.WriteLine(DateTime.Now + " Packet receieved");

            //Stores the response object that will be sent back to the android client
            OutgoingWebResponseContext response = WebOperationContext.Current.OutgoingResponse;

            String description = "User added";
            response.StatusCode = System.Net.HttpStatusCode.OK;

            //Tries to add the new user
            try
            {
                userTable.Insert(username,firstname,lastname,email,hash);
            }
            catch (SqlException e)
            {
                //Default response is a conflict
                response.StatusCode = System.Net.HttpStatusCode.Conflict;

                description = "Bad Request (" + e.Message + ")";

                //Check what the conflict is
                if (userTable.GetData().AsEnumerable().Any(row => username == row.Field<String>("username")))
                {
                    description = "Username in use";
                }
                else if (userTable.GetData().AsEnumerable().Any(row => email == row.Field<String>("email")))
                {
                    description = "Email address in use";
                }
                else
                {
                    response.StatusCode = System.Net.HttpStatusCode.BadRequest;
                }
            }

            //dsiplay and respond with the description
            Console.WriteLine(description);
            response.StatusDescription = description;

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
            Console.WriteLine("Chortle Webservice starting...");
            Console.WriteLine("Port: " + Values.port + " URL: " + Values.webserviceurl);

           try
           {
               WebServiceHost host = new WebServiceHost(typeof(ChortleService), new Uri(Values.webserviceurl));
               host.Open();
               Console.WriteLine("Host sucessfully started");
           }
           catch (Exception ex) 
           {
               Console.WriteLine("There was a problem opening the host\n");
               Console.WriteLine(ex.ToString());
           }
            
            
            while (true) ;
        }

        public void manageFirewall()
        {
            //Type NetFwMgrType = Type.GetTypeFromProgID("HNetCfg.FwMgr", false);
            //INetFwMgr mgr = (INetFwMgr)Activator.CreateInstance(NetFwMgrType);
           // bool Firewallenabled = mgr.LocalPolicy.CurrentProfile.FirewallEnabled;
        }
    }
}
