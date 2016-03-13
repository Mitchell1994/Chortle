using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using System.ServiceModel.Web;

namespace WebService
{

    [ServiceContract]
    public interface ServiceInterface
    {
        [WebInvoke(Method = "POST", UriTemplate = "users", BodyStyle = WebMessageBodyStyle.Wrapped, RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        String addUser(String username, String firstname, String lastname, String email, String hash);

        [WebGet(UriTemplate = "users/{username}", ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        User getUser(String username);

        [WebInvoke(Method = "POST", UriTemplate = "groups", BodyStyle = WebMessageBodyStyle.Wrapped, RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        String addGroup(String groupName, String groupDescription, String username);    
    }

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

        public User(ChortleDBDataSet.UserRow userDetails)
        {
            username = userDetails.Username;
            firstname = userDetails.FirstName;
            lastname = userDetails.LastName;
            email = userDetails.Email;
            hash = userDetails.PasswordHash;
        }
    }

    [DataContract]
    public class Group
    {

        [DataMember]
        public string groupName { get; set; }

        [DataMember]
        public string groupDescription { get; set; }

        public Group(ChortleDBDataSet.GroupRow groupDetails)
        {
            groupName = groupDetails.GroupName;
            groupDescription = groupDetails.Description;
        }
    }

}
