package games.negative.bot.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import games.negative.bot.Bot;
import games.negative.bot.main.config.TokenConfig;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        String token;
        TokenConfig config;

        File file = new File("config", "token.conf");
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            
            // File exists, which means the token is invalid
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("[ERROR] Could not create token.conf file");
                throw new RuntimeException(e);
            }

            config = new TokenConfig();
            try (Writer writer = new FileWriter(file)) {
                gson.toJson(config, writer);
            } catch (IOException e) {
                System.out.println("[ERROR] Could not write to token.conf file");
                throw new RuntimeException(e);
            }

            token = config.getToken();
        } else {
            try (Reader reader = new FileReader(file)) {
                config = gson.fromJson(reader, TokenConfig.class);
            } catch (IOException e) {
                System.out.println("[ERROR] Could not read from token.conf file");
                throw new RuntimeException(e);
            }

            token = config.getToken();
        }

        // Token is null, which means the token is invalid
        // So we will prompt the user in console to input their token to be
        // automatically written to the token.conf file
        if (token == null) {
            System.out.println("Please enter your bot token:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                token = reader.readLine();
            } catch (IOException e) {
                System.out.println("[ERROR] Could not read from console");
                throw new RuntimeException(e);
            }

            config.setToken(token);
            try (Writer writer = new FileWriter(file)) {
                gson.toJson(config, writer);
            } catch (IOException e) {
                System.out.println("[ERROR] Could not write to token.conf file");
                throw new RuntimeException(e);
            }
        }

        new Bot(token);
    }
}
