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

    }

}
