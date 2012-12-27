/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spinneralgorithm;

/**
 *
 * @author apoorv
 */
public class PatternUnit {
    private String arrText;
    private String [] variation;
    int askedFor = 0;
    int count;
    public PatternUnit(String text){
        this.arrText = text;
        variation = arrText.split("\\|");
        count = variation.length;
    }
    
    public int getCount(){
        return count;
    }
    
    public String getVariationWithId(int id){
        return variation[id].trim();
    }
    
    public String getVariation(){
        askedFor += 0 + (int)(Math.random() * (count));
        //System.out.println("\t\t asked for is " + askedFor);
        return variation[askedFor%count].trim();
    }
    
    public String getArrText(){
        return arrText;
    }
    
    public void displayVariation(){
        int i = 0;
        while(i < variation.length){
            System.out.println(variation[i]);
            i++;
        }
    }
}
