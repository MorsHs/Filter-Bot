package FilterBot;

import java.io.File;

public class FolderAndText {
    public static String folderLoctation() {
        return "Documents\\Java Filter\\filter folder\\";
    }

    public static boolean createOrVerify() {
        File file = new File(folderLoctation());
        if (!file.exists()) {
            if (file.mkdirs()) return true; //return true if folder created
            else return false; // failed to create
        } else return true;//return true if existed
    }
}
