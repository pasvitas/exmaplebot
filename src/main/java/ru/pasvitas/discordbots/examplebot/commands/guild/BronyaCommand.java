package ru.pasvitas.discordbots.examplebot.commands.guild;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import ru.pasvitas.discordbots.examplebot.commands.AbstractGuildCommand;
import ru.pasvitas.discordbots.examplebot.commands.BotCommand;

@BotCommand
public class BronyaCommand extends AbstractGuildCommand {

    /**
     * Название команды
     * @return команда
     */
    @Override
    public String getCommandName() {
        return "селе";
    }

    /**
     * Что сделать при выполнении
     * @param message
     */
    @Override
    public void processGuildCommand(GuildMessageReceivedEvent message) {
        message
                .getChannel()
                .sendMessage("ГДЕ?")
                .queue();
    }
}
