package ru.pasvitas.discordbots.examplebot.config;

import javax.security.auth.login.LoginException;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.pasvitas.discordbots.examplebot.listener.CommandListener;


/**
 * Конфиг дискорд-бота. В принципе служебный и все от него меняется в настройках
 */
@ConditionalOnProperty(prefix = "bot", name = "active", matchIfMissing = true)
@RequiredArgsConstructor
@Configuration
public class DiscordConfig {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.activityType}")
    private String activityType;

    @Value("${bot.activityDetails}")
    private String activityDetails;

    @Value("${bot.version}")
    private String version;

    private final CommandListener commandListener;

    @Bean
    public JDA configBot() throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.addEventListeners(commandListener);
        builder.setActivity(getCurrentActivity());
        return builder.build();
    }

    private Activity getCurrentActivity() {
        Activity discordActivity;
        String fullActivityDetails = version + " | " +  activityDetails;
        switch (activityType) {
            case "Listening":
                discordActivity = Activity.listening(fullActivityDetails);
                break;
            case "Playing":
                discordActivity = Activity.playing(fullActivityDetails);
                break;
            case "Streaming":
                discordActivity = Activity.streaming(fullActivityDetails.substring(0, fullActivityDetails.indexOf("/")),fullActivityDetails.substring(fullActivityDetails.indexOf("/")));
                break;
            case "Watching":
                discordActivity = Activity.watching(fullActivityDetails);
                break;
            default:
                discordActivity = Activity.watching("что сломалось");
                break;
        }
        return discordActivity;
    }

}
