/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spinneralgorithm;

import java.util.Map;

/**
 *
 * @author apoorv
 */
public class PatternMap {
    private Map<String,PatternUnit> patternMap;
    
    public PatternMap(Map x){
        this.patternMap = x;
    }
    
    @Override
    public String toString(){
        String str = "";
        for(Map.Entry<String,PatternUnit> x : patternMap.entrySet()){
            str += x.getKey() +":" + x.getValue().getArrText() + ",";
        }
        return str;
    }
}
