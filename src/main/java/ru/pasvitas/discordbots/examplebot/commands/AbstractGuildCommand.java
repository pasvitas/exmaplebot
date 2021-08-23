package ru.pasvitas.discordbots.examplebot.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/**
 * Служебная абстракция для команд на серверах
 */
public abstract class AbstractGuildCommand implements AbstractCommand {

    public abstract void processGuildCommand(GuildMessageReceivedEvent message);

}
