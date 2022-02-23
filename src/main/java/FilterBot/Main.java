package FilterBot;

import Commands.Commands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main extends Commands {
    public static String prefix = "?";
    private final String TOKEN = "OTI4OTYyMjU0NjM5MDc1Mzg4.YdgZhA.4oBGqySsz6bNZONt0CI06lLTK_8";

    Main() throws LoginException {
        JDA jda = JDABuilder.createDefault(TOKEN).build();
        jda.getPresence().setPresence(OnlineStatus.ONLINE, Activity.playing("?help for more commands"));
        jda.addEventListener(new Commands());
        jda.addEventListener(new Filter());
        jda.addEventListener(new Filter.EditedMessages());
    }

    public static void main(String[] args) throws LoginException {
        new Main();
        new Commands();
        new Filter();
        new Filter.EditedMessages();
    }
}
