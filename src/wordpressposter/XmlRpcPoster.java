package wordpressposter;

import redstone.xmlrpc.XmlRpcClient;
import java.util.HashMap;

public class XmlRpcPoster
{

  private String category,tags;
  
  public void setCategory(String x){
      if(x == null)
      {
          System.out.println("X is null");
          
      }else
      this.category =x.trim() ;
  }
  
  public void setTags(String x){
      this.tags =x.trim() ;
  }
  public String post(String root,String username,String password,String title,String content)
  {
    // Check command-line arguments
    
      // Get command-line arguments into variables
      if(root.charAt(root.length()-1) != '/')
          root += "/";
      String sXmlRpcURL = root+"xmlrpc.php";
      String sUsername = username;
      String sPassword = password;

      // Hard-coded blog_ID
      int blog_ID = 1;

      // XML-RPC method
      String sXmlRpcMethod = "metaWeblog.newPost";

      // We'll hard-code our blog content for now as well
      String sContent = content;
      String sTitle = title;
      String []catArray = {this.category};
      HashMap tags = new HashMap();
      tags.put("tags", this.tags);
      // Create our content struct
      HashMap hmContent = new HashMap();
      hmContent.put("title", sTitle);
      hmContent.put("description", sContent);
      hmContent.put("categories", catArray);
      hmContent.put("mt_keywords", this.tags); 
      

      // You can specify whether or not you want the blog published immediately
      boolean bPublish = true;
       String toReturn = "";
      // Try block
      try
      {
        // Create the XML-RPC client
        XmlRpcClient client = new XmlRpcClient( sXmlRpcURL, false );

        // Make our method call
        Object token = client.invoke( sXmlRpcMethod, new Object[] { new Integer( blog_ID ), sUsername, sPassword, hmContent, new Boolean( bPublish ) } );

        // The return is a String containing the postID
        toReturn =  root+"?p="+token.toString();
        System.out.println("Posted to : "+toReturn);
      }
      catch( Exception e )
      {
        e.printStackTrace( System.err );
        toReturn = "<span class='alert-error'>Not posted to " + root + "</span>";
      }
      return toReturn;
    }
  
  public String getPost(String root,String username,String password,String title,String content)
  {
    // Check command-line arguments
    
      // Get command-line arguments into variables
      String sXmlRpcURL = root+"xmlrpc.php";
      String sUsername = username;
      String sPassword = password;

      // Hard-coded blog_ID
      int blog_ID = 1;

      // XML-RPC method
      String sXmlRpcMethod = "metaWeblog.getPost";

      // We'll hard-code our blog content for now as well
      String sContent = content;
      String sTitle = title;
      HashMap tags = new HashMap();
      tags.put("tags", this.tags);
      // Create our content struct
      HashMap hmContent = new HashMap();
      hmContent.put("postid", "6");
      

      // You can specify whether or not you want the blog published immediately
      boolean bPublish = true;
       String toReturn = "";
      // Try block
      try
      {
        // Create the XML-RPC client
        XmlRpcClient client = new XmlRpcClient( sXmlRpcURL, false );

        // Make our method call
        Object token = client.invoke( sXmlRpcMethod, new Object[] { new Integer( blog_ID ), sUsername, sPassword, hmContent, new Boolean( bPublish ) } );

        // The return is a String containing the postID
        toReturn =  "Posted : "+ token.toString();
        System.out.println(toReturn);
      }
      catch( Exception e )
      {
        e.printStackTrace( System.err );
      }
      return toReturn;
    }
  
}