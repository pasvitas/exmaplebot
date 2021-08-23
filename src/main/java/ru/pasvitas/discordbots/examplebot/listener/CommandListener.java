package ru.pasvitas.discordbots.examplebot.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import liquibase.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.pasvitas.discordbots.examplebot.commands.AbstractGuildCommand;
import ru.pasvitas.discordbots.examplebot.commands.AbstractPrivateCommand;

/**
 * Занимается чтением сообщений и подбору под них команд. Все служебное и архитектурное
 */
@Slf4j
@Component
public class CommandListener extends ListenerAdapter {

    @Value("${bot.prefix}")
    private String prefix;

    private final Map<String, AbstractGuildCommand> guildCommands = new HashMap<>();

    private final Map<String, AbstractPrivateCommand> privateCommands = new HashMap<>();

    @Autowired
    public CommandListener(List<AbstractGuildCommand> guildCommandList, List<AbstractPrivateCommand> abstractPrivateCommands) {
        guildCommandList.forEach(command -> guildCommands.put(command.getCommandName(), command));
        abstractPrivateCommands.forEach(command -> privateCommands.put(command.getCommandName(), command));
        log.debug("Registered {} guild command: {}", guildCommands.size(), StringUtil.join(guildCommands.keySet(), ", "));
        log.debug("Registered {} private command: {}", privateCommands.size(), StringUtil.join(privateCommands.keySet(), ", "));
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
        if (event.getAuthor().getIdLong() == event.getJDA().getSelfUser().getIdLong()) {
            return;
        }
        if (!event.getMessage().getContentRaw().toLowerCase(Locale.ROOT).startsWith(prefix.toLowerCase(Locale.ROOT))) {
            return;
        }
        String messageWithoutPrefix = event.getMessage().getContentRaw().substring(prefix.length());
        Optional<String> command = guildCommands.keySet().stream().filter(name -> messageWithoutPrefix.trim().toLowerCase(Locale.ROOT).startsWith(name)).findAny();
        if (command.isPresent()) {
            guildCommands.get(command.get()).processGuildCommand(event);
        }
        else {
            event.getChannel().sendMessage(event.getAuthor().getAsMention() + ", неизвестная команда!").queue(success -> {
                success.delete().queueAfter(10, TimeUnit.SECONDS);
            });
        }
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        super.onPrivateMessageReceived(event);
        if (event.getAuthor().getIdLong() == event.getJDA().getSelfUser().getIdLong()) {
            return;
        }
        Optional<String> command = privateCommands.keySet().stream().filter(name -> event.getMessage().getContentRaw().toLowerCase(Locale.ROOT).startsWith(name)).findAny();
        command.ifPresent(s -> privateCommands.get(s).processPrivateCommand(event));
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
