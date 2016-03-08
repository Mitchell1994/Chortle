using System;
using System.Data.SqlClient;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using WebService.ChortleDBDataSetTableAdapters;
using System.ServiceModel.Web;

namespace WebService
{
   
    public class Service : ServiceInterface
    {

        UserTableAdapter userTable = new UserTableAdapter();

        public String addUser(String username, String firstname, String lastname, String email, String hash)
        {
            //Stores the response object that will be sent back to the android client
            OutgoingWebResponseContext response = WebOperationContext.Current.OutgoingResponse;
            String description = "User added";
            response.StatusCode = System.Net.HttpStatusCode.OK;

            //Tries to add the new user
            try
            {
                userTable.Insert(username, firstname, lastname, email, hash);
            }
            catch (SqlException e)
            {
                //Default response is a conflict
                response.StatusCode = System.Net.HttpStatusCode.Conflict;

                description = "Bad Request (" + e.Message + ")";

                //Check what the conflict is
                if (userTable.GetData().AsEnumerable().Any(row => row.Username == username))
                {
                    description = "Username in use";
                }
                else if (userTable.GetData().AsEnumerable().Any(row => row.Email == email))
                {
                    description = "Email address in use";
                }
                else
                {
                    response.StatusCode = System.Net.HttpStatusCode.BadRequest;
                }
            }

            //respond with the description
            response.StatusDescription = description;
            return description;
        }
    }
}
