package lolcatloyal.Iggy.listeners;

import lolcatloyal.Iggy.Iggy;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import javax.xml.soap.Text;
import java.util.List;

/**
 * Listener that welcomes new non-bot
 * server members with an @ message.
 */
public class WelcomeListener extends ListenerAdapter {

    private static final String WELCOME_MESSAGE = "Welcome ";

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        User newMember = event.getUser();

        if (!newMember.isBot()){
            String mention = newMember.getAsMention();

            List<TextChannel> welcomeChannels = event.getGuild().getTextChannelsByName(Iggy.WELCOME_CHANNEL_NAME, true);

            if (!welcomeChannels.isEmpty()){
                //Send welcome message in first welcome text channel
                welcomeChannels.get(0).sendMessage(WELCOME_MESSAGE + mention).queue();
            }
        }
    }

}
