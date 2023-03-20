package games.negative.bot.main.config;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class TokenConfig {

    @SerializedName("token")
    private String token = null;
}
