import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FilterBotCommands<onMessageReceived> extends ListenerAdapter {
    private static FolderAndTxt FAT = new FolderAndTxt();
    private static String filterpath = FolderAndTxt.FilterLocation();

    public void onMessageReceived(MessageReceivedEvent event) {
        String filetext = event.getMessage().getContentRaw(); //get message as a whole
        String[] userinput = filetext.split(" "); //recieve the message then split it by space
        if (userinput[0].equals(FilterBotMain.prefix + "help") && event.getAuthor().isBot() == false) {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(">help = Shows Commands\n>filteradd = Adds words you want to filter").queue();
        } else if (userinput[0].equals(FilterBotMain.prefix + "filteradd") && event.getAuthor().isBot() == false) {
            File file = new File(FolderAndTxt.FilterLocation().concat("filter.txt"));
            //  boolean checker = FolderAndTxt.CreateOrVerify();
            if (FolderAndTxt.CreateOrVerify() == true) {
                try {
                    FileWriter fw = new FileWriter(file, true);//true to not overwrite
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(userinput[1] + "\n");
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                event.getChannel().sendMessage("There's a Problem in the Server Side. Please contact the developer.").queue();
        } else if (event.getAuthor().isBot() == false) {
            long msgid = event.getMessageIdLong();
            try {
                if (scanMessages(userinput) == true) {
                    event.getChannel().deleteMessageById(msgid).queue();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//            for(int i =0;i < userinput.length;i++){
//                try {
//                    if(filetext.contains(scanMessages())) {
//                        event.getChannel().deleteMessageById(msgid).queueAfter(1, TimeUnit.NANOSECONDS);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }
    
    public static boolean scanMessages(String usermessage[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FolderAndTxt.FilterLocation().concat("filter.txt")));
        List<String> list = new ArrayList<String>();
        int index = 0;
        while (true) {
            list.add(br.readLine());
            if (list.get(index) == null) break;
            // System.out.println(list.get(index)); // Test output
            index++;
        }
        for (int i = 0; i < list.size() - 1; i++) {
//            if(filetext.contains(list.get(i))) System.out.println(true);    //uncomment if performance is bad
//            else System.out.println(false); // test
            for (int j = 0; j < usermessage.length; j++) {
                if (usermessage[j].contains(list.get(i))) return true;
            }
        }
        return false;
    }
}