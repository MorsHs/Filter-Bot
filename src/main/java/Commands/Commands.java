package Commands;

import FilterBot.FolderAndText;
import FilterBot.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Commands extends ListenerAdapter {

    //@Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] input = event.getMessage().getContentRaw().split(" ");
        if (input[0].equals(Main.prefix + "help") && !event.getAuthor().isBot()) {
            event.getChannel().sendMessage("?help to Show Commands\n?filteradd to add a word to filter").queue();
        } else if (input[0].equals(Main.prefix + "filteradd") && !event.getAuthor().isBot()) {
            File file = new File(FolderAndText.folderLoctation().concat("filter.txt"));
            if (FolderAndText.createOrVerify() == true) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                    bw.write(input[1] + "\n");
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onMessageReceived(event);
    }

}
