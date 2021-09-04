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

    private static final String[] WELCOME_MESSAGES = {" Yare Yare, feel free to be helpful and join the chat",
            " It's not like we like you here or anything b-b-b-baka",
            " Shishishishishi, nice to see you here", " N..nani?? Where'd you come from?!",
            " Muramuramuramura, welcome to anime club!!!!"};

    private final Random r;

    public WelcomeListener() {
        r = new Random();
    }

    /**
     * When a member joins the server, a welcome message will be
     * sent in the welcome channel if the new member is not
     * a bot.
     * @param event GuildMemberJoinEvent signalling a new member
     *              has joined the server.
     */
    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        User newMember = event.getUser();

        if (newMember.isBot()){ //TODO: add !
            List<TextChannel> welcomeChannels = event.getGuild().getTextChannelsByName(Iggy.WELCOME_CHANNEL_NAME, true);

            if (!welcomeChannels.isEmpty()){
                String mention = newMember.getAsMention();

                //Send welcome message in first welcome text channel
                welcomeChannels.get(0).sendMessage(mention + chooseWelcomeMessage()).queue();
            }
        }
    }

    /**
     * Choose and return a random welcome
     * message from the array of welcome
     * messages.
     *
     * @return A random welcome message.
     */
    private String chooseWelcomeMessage(){
        int i = r.nextInt(WELCOME_MESSAGES.length);
        return WELCOME_MESSAGES[i];
    }



}
