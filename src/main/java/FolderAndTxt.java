import java.io.File;

public class FolderAndTxt {
    public static boolean CreateOrVerify(){
        File mainfolder = new File(FilterLocation());
        if(!mainfolder.exists()){
            if(mainfolder.mkdirs()){
                return true;
            }
            else return false;  // return false if failed
        }
        else return true; // return false if excisted
    }
    public static String FilterLocation(){
        return "Documents\\Java Filter\\filter folder\\";
    }
}
