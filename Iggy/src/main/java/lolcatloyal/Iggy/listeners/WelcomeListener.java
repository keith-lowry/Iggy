package lolcatloyal.Iggy.listeners;

import lolcatloyal.Iggy.Iggy;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

/**
 * Listener that welcomes new non-bot
 * server members with an @ message.
 */
public class WelcomeListener extends ListenerAdapter {

    private static final String[] WELCOME_MESSAGES = {"We've been expecting you ", "Welcome ", "Hey there "};

    private final Random r;

    public WelcomeListener() {
        r = new Random();
    }
    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        User newMember = event.getUser();

        if (newMember.isBot()){ //TODO: add !
            String mention = newMember.getAsMention();

            List<TextChannel> welcomeChannels = event.getGuild().getTextChannelsByName(Iggy.WELCOME_CHANNEL_NAME, true);

            if (!welcomeChannels.isEmpty()){
                //Send welcome message in first welcome text channel
                welcomeChannels.get(0).sendMessage(chooseWelcomeMessage() + mention).queue();
            }
        }
    }

    /**
     * Choose and return a random welcome
     * message from the array of welcome
     * messages
     * @return
     */
    private String chooseWelcomeMessage(){
        int i = r.nextInt(WELCOME_MESSAGES.length);
        return WELCOME_MESSAGES[i];
    }



}
