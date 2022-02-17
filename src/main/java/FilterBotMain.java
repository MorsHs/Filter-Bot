import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class FilterBotMain {
    public static String prefix = ">";
    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault("Your Token").build();
        jda.getPresence().setPresence(OnlineStatus.ONLINE, Activity.watching("Your Dirty Mouth"));
        jda.addEventListener(new FilterBotCommands());
    }
}
