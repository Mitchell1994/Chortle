using System;
using System.Data.SqlClient;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using WebService.ChortleDBDataSetTableAdapters;
using System.ServiceModel.Web;
using System.Data;

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

            //Tries to add the new user
            try
            {
                userTable.Insert(username, firstname, lastname, email, hash);
                response.StatusCode = System.Net.HttpStatusCode.OK;
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

        public User getUser(String username)
        {
            //Stores the response object that will be sent back to the android client
            OutgoingWebResponseContext response = WebOperationContext.Current.OutgoingResponse;

            Console.WriteLine("getting");

            ChortleDBDataSet.UserRow[] users = (from userData in userTable.GetData().AsEnumerable()
                                where userData.Username == username
                                select userData).ToArray();
            

            if(users.Length == 1)
            {
                response.StatusCode = System.Net.HttpStatusCode.Found;
                ChortleDBDataSet.UserRow userDetails = users[0];
                User user = new User(userDetails);
                response.StatusCode = System.Net.HttpStatusCode.Found;
                response.StatusDescription = "User found";
                return user;
            }
            else if(users.Length == 0){
                response.StatusCode = System.Net.HttpStatusCode.NotFound;
                response.StatusDescription = "User not found";
                return null;
            }
            else
            {
                response.StatusCode = System.Net.HttpStatusCode.InternalServerError;
                response.StatusDescription = "Something went wrong";
                return null;
            }
        }
    }
}
