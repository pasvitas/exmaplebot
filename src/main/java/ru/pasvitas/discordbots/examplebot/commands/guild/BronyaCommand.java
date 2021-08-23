package ru.pasvitas.discordbots.examplebot.commands.guild;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import ru.pasvitas.discordbots.examplebot.commands.AbstractGuildCommand;
import ru.pasvitas.discordbots.examplebot.commands.BotCommand;

@BotCommand //Помечает, что это бот-команда и её надо регистрировать
public class BronyaCommand extends AbstractGuildCommand { //Наследуемся от абстракции для сервера, так как команда серверная

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
