package games.negative.bot;

import games.negative.framework.discord.DiscordBot;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.List;

public class Bot extends DiscordBot {

    public Bot(String token) {

        List<GatewayIntent> intents = List.of(
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MEMBERS
        );

        JDABuilder builder = create(token, intents);

    }
}
