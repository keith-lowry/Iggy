package lolcatloyal.Iggy;

import lolcatloyal.Iggy.listeners.WelcomeListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;


public class Iggy {

    public static JDABuilder builder;

    public static final String WELCOME_CHANNEL_NAME = "welcome";

    /**
     * Build the Iggy welcome bot.
     *
     * @param args An array containing the bot's token.
     * @throws LoginException if the provided token is invalid.
     */
    public static void main(String[] args) throws LoginException {
        builder = JDABuilder.createDefault(args[0]);

        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);

        builder.setCompression(Compression.NONE);

        builder.setActivity(Activity.playing("Welcome"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);

        registerListeners();

        builder.build();
    }

    /**
     * Add Welcome and Settings listeners.
     */
    private static void registerListeners(){
        builder.addEventListeners(new WelcomeListener());
    }
}
