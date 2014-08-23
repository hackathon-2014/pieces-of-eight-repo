package com.mush4brains.phoenix;

//call
//private GlobalConstants mConstants = GlobalConstants.getInstance();
public class GlobalConstants {

    private static GlobalConstants instance = new GlobalConstants();
    private GlobalConstants(){}
    public static GlobalConstants getInstance(){
    return instance;
    }
    
    public int ICON_MAIN_NINJA = 100;
    public int ICON_MAIN_PIRATE = 101;
    public int ICON_MAIN_ZOMBIE = 102;
    
}
