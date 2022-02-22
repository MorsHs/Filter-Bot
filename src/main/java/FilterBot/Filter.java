package FilterBot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Filter extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        String[] input = e.getMessage().getContentRaw().split(" ");
        try {
            if (scanMessage(input) == true) {
                e.getMessage().delete().queue();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        super.onMessageReceived(e);
    }


    public boolean scanMessage(@NotNull String[] usertransfer) throws IOException {
        BufferedReader bw = new BufferedReader(new FileReader(FolderAndText.folderLoctation().concat("filter.txt")));
        List<String> list = new ArrayList<String>();
        int index = 0;
        while (true) {
            list.add(bw.readLine());
            if (list.get(index) == null) break;
            index++;
        }
        for (int i = 0; i < usertransfer.length; i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (usertransfer[i].equalsIgnoreCase(list.get(j))) return true;
            }
        }
        return false;
    }
}
