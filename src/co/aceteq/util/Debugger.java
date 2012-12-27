/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.util;

/**
 *
 * @author apoorv
 */
public class Debugger {
    public static int level = 20;
    /*
     * level = 1 : error
     * level = 2 : warning
     * level = 10 : debug
     * level = 20 : info
     */
    public static void display(String msg, int l)
    {
           if(l > level)
           {
               return;
           }
           System.out.println(msg);
    }
}
