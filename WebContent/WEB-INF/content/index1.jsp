<%-- 
    Document   : index
    Created on : Jun 17, 2011, 7:18:53 PM
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
         <!-- OF COURSE YOU NEED TO ADAPT NEXT LINE TO YOUR tiny_mce.js PATH -->
        <script type="text/javascript" src="tinymce/jscripts/tiny_mce/tiny_mce.js"></script>

        <script type="text/javascript">
        tinyMCE.init({
                mode : "textareas",
                theme : "advanced",
                content_css : "css/tinyMCE.css",
                theme_advanced_toolbar_location : "top",
                theme_advanced_toolbar_align : "left"
        });
        
        </script>

        <title>Article Demon</title>
    </head>
    <body>
        <div class="body-container">
            <div class="container_16">
                <div class="grid_6 alpha">
                    <h1>Article Demon</h1>
                </div>
                <div class="grid_10 omega">
                </div>
            </div>
          </div><!-- first container closed -->
          <br />
          <div class="form-container">
              <form action="Spin" method="post">
                  <div class="container_16">
                      <div class="grid_16 alpha omega">
                          <div class="title-textarea-container">
                              Title
                              <textarea  tabindex=1 autocomplete="off" id="titleSpintax" class="ui-corner-all width100 boxInnerShadow textInputPadding font13 dimText50" name="title"></textarea>
                          </div>
                       </div>
                  </div> <!-- close container for title spintax -->

                  <div class="container_16 down_20">
                      <div class="grid_16 alpha omega">
                          <div class="title-textarea-container">
                              Article
                              <textarea  tabindex=2 autocomplete="off" id="articleSpintax" rows="20" class="ui-corner-all width100 boxInnerShadow textInputPadding font13 dimText50" name="article"></textarea>
                          </div>
                       </div>
                  </div> <!-- close container for article spintax -->
                  
                  <div class="container_16 down_20">
                      <div class="grid_5 alpha">
                              <input tabindex=3 class="" placeholder="Number of Articles" type="text" name="count" />
                              <br /><br /><input tabindex=6 type="submit" value="Spin" />    
                       </div>
                      <div class="grid_5">
                          <input tabindex=4 class="" placeholder="Tags" type="text" name="tags" />
                      </div>
                      <div class="grid_6 omega">
                          <input tabindex=5 class="" placeholder="Category" type="text" name="category" />                          
                      </div>
                  </div>
              </form>
           </div>
          <br /><br /><br />
    </body>
</html>
