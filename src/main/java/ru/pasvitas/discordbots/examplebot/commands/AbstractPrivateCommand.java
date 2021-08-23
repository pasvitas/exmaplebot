package ru.pasvitas.discordbots.examplebot.commands;

import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

/**
 * Служебная абстракция для приватных команд
 */
public abstract class AbstractPrivateCommand implements AbstractCommand {

    public abstract void processPrivateCommand(PrivateMessageReceivedEvent message);

}
