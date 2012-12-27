/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SpinnerTest;

import spinneralgorithm.SpinnerAlgorithm;

/**
 *
 * @author apoorv
 */
public class Main {
    public static void main(String[] args) {
        SpinnerAlgorithm sa = new SpinnerAlgorithm("{Apoorv{u|v} |Parijat| Graybeard| } is a {{really|way to} awesome|great} programmer.");
        String spun[] = sa.getAllPossibleSpins();
        for(String s : spun){
            System.out.println(s);
        }
    }
    
}
