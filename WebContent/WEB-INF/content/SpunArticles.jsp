<%-- 
    Document   : SpunArticles
    Created on : Jun 18, 2011, 12:03:26 AM
    Author     : apoorv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/960grid.css" />
        <link rel="stylesheet" type="text/css" href="css/spinner.css" />
        <link href='http://fonts.googleapis.com/css?family=Caudex&v1' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="css/ui-lightness/jquery-ui-1.8.13.custom.css" />
        <script type="text/javascript" src="jqueryui/js/jquery-1.5.1.min.js"></script>
        <script type="text/javascript" src="jqueryui/js/jquery-ui-1.8.13.custom.min.js"></script>
        <script type="text/javascript" src="js/spinner.js"></script>
        <script type="text/javascript" src="js/jquery.animate-shadow.js"></script>
        <title>Spun Articles</title>
    </head>
    <body>
        <input type="hidden" name="tags" id="tags" value="<%=session.getAttribute("tags")%>"/>
        <input type="hidden" name="category" id="category" value="<%=session.getAttribute("category")%>"/>
        
        <div class="container_16">
            <div class="grid_6 alpha" id="navButtons">
                
            </div>
            <div class="grid_16 omega">
            </div>
        </div>
        
        
        <%
            String [] st = (String [])session.getAttribute("spunTitles");
            String [] sa = (String [])session.getAttribute("spunArticles");
            int noTitle = st.length;
            int noArticle = sa.length;
            int i = 0;
            int set = 0;
            int j = 0;
            int numOfArticles = Integer.parseInt((String)session.getAttribute("count"));
            System.out.println("required/present: " + numOfArticles + "/"+noArticle);
            while((i < numOfArticles) && (i < noArticle)){
                
        %>
        <div class="post-holder set set<%=set%>">
                   <div class="container_16 down_20">
                      <div class="grid_16 alpha omega">
                          <div id="title<%=i%>" class="titleHolder"><%=st[j]%></div>
                          <div id="article<%=i%>" class="articleHolder"><%=sa[i]%></div>
                       </div>
                  </div> <!-- close container for title and article holder -->
                  <div class="container_16 down_10 no-background">
                      <div class="grid_3 alpha">
                          <input type="button" value="Post to Wordpress" onclick="fillDetails(<%=i%>)" />
                       </div>
                       <div class="grid_13 omega">
                           <div class="send-status"><img class="loader" id="loader<%=i%>" src="images/loader.gif" alt="sending .." /><span class="submitted" id="submitted<%=i%>">Submitted</span></div>
                       </div>
                  </div> <!-- close container for title and article holder -->
                  <div class="container_16 no-background">
                      
                    <div class="zoom-popup" id="popup<%=i%>">
                            <br />
                            <input type="text" class="ui-corner-all boxShadow incLineHeight" id="root<%=i%>" placeholder="Your Wordpress Root" /><br />
                            <input type="text" class="ui-corner-all boxShadow incLineHeight" id="username<%=i%>" placeholder="Wordpress username" /><br />
                            <input type="text" class="ui-corner-all boxShadow incLineHeight" id="password<%=i%>" placeholder="Wordpress password" /><br /><br />
                            <input type="button" value="Send Article" onclick="sendToWordpress(<%=i%>)"/>
                            <a class="closeText" onclick="hide(<%=i%>)">Close</a>
                    </div>
                  </div>
        </div>
        <%
            i++;
            j++;
            if(j >= noTitle-1){
                j = 0;
            }
            if(i%5 == 0)
            {
                set++;
            }
        }
        %>
        <script type="text/javascript">
            var sets = <%=set%>;
        </script>
        <br />
        <br /><br />
        <br />
    </body>
</html>
