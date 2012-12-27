/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.ajax.actions;

import co.aceteq.ajax.AjaxAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import spinneralgorithm.PatternMap;
import spinneralgorithm.SpinnerAlgorithm;
import wordpressposter.XmlRpcPoster;

/**
 *
 * @author apoorvparijat
 */
public class PostToNetwork extends AjaxAction  {
  
    Map<String,Object> hs;
    
    public PostToNetwork(HttpServletRequest fd){
        super(fd);
    }
    
    @Override
    public void execute(){
         if(!super.formData.getParameter("password").equals("graybeard123")){
             super.responseData = "";
             super.progress = "{\"status\":\"error\",\"msg\":\"Wrong Network password.\",\"progress\":\"100\"}";
             return;
         }
         super.responseData = "Started spinning ..";
         super.progress = "Starting thread.";
         hs = new HashMap<String,Object>();
         hs.put("tags", this.formData.getParameter("tags"));
         //System.out.println(hs.get("tags"));
         hs.put("category", this.formData.getParameter("category"));
         hs.put("title",this.formData.getParameter("title"));
         hs.put("count",this.formData.getParameter("numArticles"));
         hs.put("article",this.formData.getParameter("article"));
         Thread t = new Thread(new TestingProgress(this));
         t.start();
         return;
    }
    
    public boolean startSpinning(){
        int count = Integer.parseInt((String)hs.get("count"));
        try{
            String title = (String) hs.get("title");
            if(title.length() >5000){
                super.progress = "{\"status\":\"error\",\"msg\":\"Title too long.\",\"progress\":\"100\"}";
                return false;
            }
            String article = (String) hs.get("article");
            System.out.println(article.length());
            if(article.length() >15000){
                super.progress = "{\"status\":\"error\",\"msg\":\"Article too long.\",\"progress\":\"100\"}";
                return false;
            }
            article = article.replaceAll("\n", "<br />");
            SpinnerAlgorithm st = new SpinnerAlgorithm(title);
            String [] spunTitles = st.getSpun(count);
            super.progress = "{\"status\":\"working\",\"msg\":\"Done spinning title.\",\"progress\":\"10\"}";
            SpinnerAlgorithm sa = new SpinnerAlgorithm(article);
            String [] spunArticles = sa.getSpun(count);
            super.progress = "{\"status\":\"working\",\"msg\":\"Done spinning article.\",\"progress\":\"35\"}";
            
            hs.put("spunArticles", spunArticles);
            hs.put("spunTitles", spunTitles);
            hs.put("count", count);
            
            PatternMap pm = new PatternMap(sa.getPatternMap());
            PatternMap pm2 = new PatternMap(st.getPatternMap());
            System.out.println("Done with startSpinning");
            return true;
        }catch(Exception e){
            super.progress = "{\"status\":\"error\",\"msg\":\"Error in Spinning.<br/> "+e.getMessage()+"\",\"progress\":\"100\"}";
            return false;
        }
    }
    
    public void startPosting(){
        String category = (String) hs.get("category");
        String tags = (String) hs.get("tags");
        String [] st = (String [])hs.get("spunTitles");
        String [] sa = (String [])hs.get("spunArticles");
        int noTitle = st.length;
        int noArticle = sa.length;
        int i = 0;
        int set = 0;
        int j = 0;
        
        int numOfArticles = Integer.parseInt(hs.get("count")+"");
        String [] hosts = {"http://www.usciif09.com","http://www.whocaresreally.info","http://www.vhcherry.com","http://www.unpredictablered.com","http://www.twcnotes.com","http://www.therehavebeentimes.info","http://www.speedingupthebrains.info","http://www.shotsnottaken.info","http://www.ranthecheckfailed.info","http://www.productivityeasy.info","http://www.niepf.com","http://www.mycoolweb.net","http://www.knowledgebaseblog.info","http://www.inthebooks.info","http://www.ignuf.net","http://www.healingthosewhoserve.net","http://www.eaglenergysol.com","http://www.callmescentsy.com","http://www.buzz-articles.info","http://www.whatstheretoknow.com","http://www.bleedcardinal.com","http://www.akaash.info","http://www.bestdoglove.com"};
        String [] usernames = {"Bruce","Russell","Shane","gabe","Tony","Valdmir","Evra","Sam","Cory","Rick","Mavrick","Jake","Andrio","Mike","Andy","Franklin","Straut","John","Carl","Michael","Harry","admin","Chris"};
        String [] passwords = {"ebjg2%@me123","HkbBaZ#S1VcW","A&(Ysytug13(","$gabe=$user;","#YhGT#wZ7Wsa","aj2F3MBy8I%A","ajRBjU4HaZ#d","ywrLcx&A5PjA","2yeOhw3ZWCfL","(ie@Hfx9e7Lr","qnujdU(MJJe9","^NXzWMWg@@h@","ys79Dk&YR5*Y","ln%oJfIxw)Wd","1sx@y^TTYAvf","yRYiJiq9K9G#","t9FV4(C#z50O","U!@o1mP63(B4","CMg&2g7LEfVx","896hysidds","ebjg2%@me123","ebjg2%@me123","$new=\"123\";"};
        //String [] hosts = {"http://localhost/~apoorvparijat/wordpress"};
        //String [] usernames = {"admin"};
        //String [] passwords = {"shonen"};
        int totalDomains = hosts.length;
        int numOfDomains = hosts.length;
        System.out.println("numOfArticles = " + numOfArticles + " noArticle = " + noArticle);
        double percent = 35;
        String resp = "";
        while(numOfDomains > 0 && (i < numOfArticles) && (i < noArticle)){
            String title = st[j];
            System.out.println(title);
            String article = sa[i];
            String root = hosts[i];
            if(sa[i].length() >= 1500){
                String username = usernames[i];
                String password = passwords[i];
                XmlRpcPoster poster = new XmlRpcPoster();
                percent = 35+((double)(totalDomains-numOfDomains)/(double)totalDomains)*65;
            //System.out.println(percent);
                poster.setCategory(category);
                poster.setTags(tags);                
                String result = poster.post(root, username, password, title, article);
                resp+=result+"<br/>";
                super.progress = "{\"status\":\"working\",\"msg\":\"Submissions remaining : "+(numOfArticles-i)+"<br/><br/>"+resp+"\",\"progress\":\""+percent+"\"}";
            }else{
                percent = 35+((double)(totalDomains-numOfDomains)/(double)totalDomains)*65;
                resp+="<span class='alert-error'>Not Posted to " + root +". Article < 300 words.</span><br/>";
                super.progress = "{\"status\":\"working\",\"msg\":\"Submissions remaining : "+(numOfArticles-i)+"<br/><br/>"+resp+"\",\"progress\":\""+percent+"\"}";
            }
            
            i++;
            j++;
            if(j >= noTitle-1){
                j = 0;
            }
            if(i%5 == 0)
            {
                set++;
            }
            numOfDomains--;
        }
        super.progress = "{\"status\":\"completed\",\"msg\":\"Completed Posting to Network Successfully.<br/><br/>"+resp+"\",\"progress\":\"100\"}";
        
    }


    public void setProgress(String str){
        super.progress = str;
    }
    
    static class TestingProgress implements Runnable {

        PostToNetwork ps;
        private TestingProgress(PostToNetwork aThis) {
            this.ps = aThis;
        }

        @Override
        public void run() {
            
            if(ps.startSpinning()){
                System.out.println("Spinned w/o error. Now posting.. ");
                ps.startPosting();
            }
        }
    }
    
}