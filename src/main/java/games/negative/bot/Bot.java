package games.negative.bot;

import games.negative.framework.discord.DiscordBot;
import net.dv8tion.jda.api.JDA;
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

        // In this middle part is where you should be
        // registering most of your own logic, commands, etc.

        JDA jda;
        try {
            jda = builder.build().awaitReady();
        } catch (InterruptedException e) {
            System.out.println("Failed to build JDA");
            throw new RuntimeException(e);
        }

        initializeCommands(jda);
    }
}
