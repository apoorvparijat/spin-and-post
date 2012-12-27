/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spinneralgorithm;

import co.aceteq.util.Debugger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author apoorv
 */
public class SpinnerAlgorithm {

    /**
     * @param args the command line arguments
     */
    private String article;
    private Map <String,PatternUnit> patternMap;
    private int count,req;
    private ArrayList <String> spun;
    private Pattern spinPattern;
    private Pattern unitPattern;
    int co = 0;
    int s= 0;
    int lastSpinAt =0;
    public SpinnerAlgorithm(String text)
    {
        count = 0;
        article = text;
        spun = new ArrayList<String>();
        patternMap = new HashMap<String,PatternUnit>();
        spinPattern = Pattern.compile("(pattern([0-9])+?)");
        unitPattern = Pattern.compile("\\{([^\\{\\}]*?)\\}");
    }
    
    public String  getCompiledArticle(String x){
        //System.out.println("compiled called with " + x);
        Matcher m = unitPattern.matcher(x);
        StringBuilder articleBuffer = new StringBuilder(x);
        PatternUnit pu;
        while(m.find()){
            articleBuffer.replace(m.start(), m.end(), "pattern"+count);
            pu = new PatternUnit(m.group(1));
            //System.out.println(m.group(1));
            patternMap.put("pattern"+count, pu);
            count++;
            m = unitPattern.matcher(articleBuffer.toString());
            //System.out.println("co = " + co++);
        }
        return articleBuffer.toString();
    }
    
    public String compile() {
       String newArticle = getCompiledArticle(this.article);
       //System.out.println("Compiled string : "+newArticle);
       return newArticle;
    }
    
    public void spin(String compiledArticle){
        int level = count-1;
        while(req > 0){
            StringBuffer sb = new StringBuffer(compiledArticle);
            //System.out.println("req =: " + req);
            level = count-1;
            //System.out.println("Entering level loop " + level);
            while(level >= 0){
                
                String key = "pattern"+level;
                PatternUnit pu = patternMap.get(key);
                //System.out.println("Search for "+ key + " in > \n " + sb.toString() + "\n");
                String article = Pattern.compile("("+key+")").matcher(sb.toString()).replaceAll(pu.getVariation());
                sb = new StringBuffer(article);
                //System.out.println(article);
                level--;
            }
            if(level < 0){
                    spun.add(sb.toString());
                    req--;
            }
        }
    }
    
    public void spinAll(String compiledArticle,int level){
        String terminatePattern = "pattern([0-9])";
        Matcher x = Pattern.compile(terminatePattern).matcher(compiledArticle);
        if(!x.find()){
            spun.add(compiledArticle);
            return;
        }
        StringBuilder sb;
        String key = "pattern"+x.group(1);
        //System.out.println("Finding : " + key + " in : " + compiledArticle);
        Matcher y = Pattern.compile("("+key+")").matcher(compiledArticle);
        if(y.find()){
          PatternUnit pu = patternMap.get(key);
          for(int i = 0; i < pu.getCount(); i++){
            sb =  new StringBuilder(compiledArticle);
            sb.replace(y.start(), y.end(), pu.getVariationWithId(i));
            spinAll(sb.toString(),level-1);
            //System.out.println("calling spinAll with " + sb.toString());
          }
        }
    }
    
    public String [] getSpun(int x){
        req =x;
        this.spin(this.compile());
        String [] s = new String[spun.size()];
        spun.toArray(s);
        return s;
    }
    
    public String [] getAllPossibleSpins(){
        this.spinAll(this.compile(),count-1);
        String [] s = new String[spun.size()];
        spun.toArray(s);
        return s;
    }
    
    public Map getPatternMap(){
        return this.patternMap;
    }
}
