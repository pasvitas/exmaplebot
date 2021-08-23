package ru.pasvitas.discordbots.examplebot.listener;

import java.util.List;
import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageActivity;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.pasvitas.discordbots.examplebot.commands.AbstractGuildCommand;
import ru.pasvitas.discordbots.examplebot.commands.AbstractPrivateCommand;

class CommandListenerTest {

    private final AbstractGuildCommand abstractGuildCommand1 = Mockito.mock(AbstractGuildCommand.class);
    private final AbstractGuildCommand abstractGuildCommand2 = Mockito.mock(AbstractGuildCommand.class);
    private final AbstractGuildCommand abstractGuildCommand3 = Mockito.mock(AbstractGuildCommand.class);

    private final AbstractPrivateCommand abstractPrivateCommand1 = Mockito.mock(AbstractPrivateCommand.class);
    private final AbstractPrivateCommand abstractPrivateCommand2 = Mockito.mock(AbstractPrivateCommand.class);

    private final GuildMessageReceivedEvent event = Mockito.mock(GuildMessageReceivedEvent.class);
    private final Message message = Mockito.mock(Message.class);
    private final User author = Mockito.mock(User.class);
    private final SelfUser selfUser = Mockito.mock(SelfUser.class);
    private final JDA jda = Mockito.mock(JDA.class);


    private final List<AbstractGuildCommand> abstractGuildCommandList = List.of(
            abstractGuildCommand1,
            abstractGuildCommand2,
            abstractGuildCommand3
    );

    private final List<AbstractPrivateCommand> abstractPrivateCommandsList = List.of(
            abstractPrivateCommand1,
            abstractPrivateCommand2
    );

    private CommandListener commandListener;

    @BeforeEach
    void setUp() {
        Mockito.when(abstractGuildCommand1.getCommandName()).thenReturn("команда 1");
        Mockito.when(abstractGuildCommand2.getCommandName()).thenReturn("команда 2");
        Mockito.when(abstractGuildCommand3.getCommandName()).thenReturn("команда 3");
        Mockito.when(abstractPrivateCommand1.getCommandName()).thenReturn("команда 1");
        Mockito.when(abstractPrivateCommand2.getCommandName()).thenReturn("команда 4");
        commandListener = new CommandListener(abstractGuildCommandList, abstractPrivateCommandsList);
        commandListener.setPrefix("!");

        Mockito.when(message.getAuthor()).thenReturn(author);
        Mockito.when(message.getJDA()).thenReturn(jda);
        Mockito.when(event.getAuthor()).thenReturn(author);
        Mockito.when(event.getJDA()).thenReturn(jda);
        Mockito.when(event.getMessage()).thenReturn(message);
        Mockito.when(jda.getSelfUser()).thenReturn(selfUser);
        Mockito.when(selfUser.getIdLong()).thenReturn(1L);
        Mockito.when(author.getIdLong()).thenReturn(2L);
    }

    @DisplayName("Check guild command 1")
    @Test
    void processGuildCommand1() {
        Mockito.when(message.getContentRaw()).thenReturn("!команда 1");
        commandListener.onGuildMessageReceived(event);
        Mockito.verify(abstractGuildCommand1, Mockito.times(1)).processGuildCommand(event);


        Mockito.when(message.getContentRaw()).thenReturn("!команда 1 с текстом");
        commandListener.onGuildMessageReceived(event);
        Mockito.verify(abstractGuildCommand1, Mockito.times(2)).processGuildCommand(event);


        Mockito.when(message.getContentRaw()).thenReturn("!коМАнда 1 с ТЕКстом");
        commandListener.onGuildMessageReceived(event);
        Mockito.verify(abstractGuildCommand1, Mockito.times(3)).processGuildCommand(event);

        Mockito.verify(abstractGuildCommand2, Mockito.never()).processGuildCommand(Mockito.any());
        Mockito.verify(abstractGuildCommand3, Mockito.never()).processGuildCommand(Mockito.any());
        Mockito.verify(abstractPrivateCommand1, Mockito.never()).processPrivateCommand(Mockito.any());
        Mockito.verify(abstractPrivateCommand2, Mockito.never()).processPrivateCommand(Mockito.any());

    }

    @DisplayName("Check guild command several")
    @Test
    void processGuildCommandSeveral() {
        Mockito.when(message.getContentRaw()).thenReturn("!команда 1");
        commandListener.onGuildMessageReceived(event);
        Mockito.verify(abstractGuildCommand1, Mockito.times(1)).processGuildCommand(event);


        Mockito.when(message.getContentRaw()).thenReturn("!команда 2");
        commandListener.onGuildMessageReceived(event);
        Mockito.verify(abstractGuildCommand2, Mockito.times(1)).processGuildCommand(event);

        Mockito.verify(abstractGuildCommand3, Mockito.never()).processGuildCommand(Mockito.any());
        Mockito.verify(abstractPrivateCommand1, Mockito.never()).processPrivateCommand(Mockito.any());
        Mockito.verify(abstractPrivateCommand2, Mockito.never()).processPrivateCommand(Mockito.any());
    }

    @DisplayName("For same user")
    @Test
    void processGuildCommandSameUser() {

        Mockito.when(author.getIdLong()).thenReturn(1L);
        Mockito.when(message.getContentRaw()).thenReturn("!команда 1");
        commandListener.onGuildMessageReceived(event);
        Mockito.verify(abstractGuildCommand1, Mockito.never()).processGuildCommand(event);
        Mockito.verify(abstractGuildCommand2, Mockito.never()).processGuildCommand(Mockito.any());
        Mockito.verify(abstractGuildCommand3, Mockito.never()).processGuildCommand(Mockito.any());
        Mockito.verify(abstractPrivateCommand1, Mockito.never()).processPrivateCommand(Mockito.any());
        Mockito.verify(abstractPrivateCommand2, Mockito.never()).processPrivateCommand(Mockito.any());
    }

    @DisplayName("Unknown command")
    @Test
    void processGuildCommandUnknown() {

        TextChannel channel = Mockito.mock(TextChannel.class);
        MessageAction action = Mockito.mock(MessageAction.class);
        Mockito.when(author.getAsMention()).thenReturn("Mention");

        Mockito.when(event.getChannel()).thenReturn(channel);
        Mockito.when(channel.sendMessage("Mention, неизвестная команда!")).thenReturn(action);

        Mockito.when(message.getContentRaw()).thenReturn("!команда 0");
        commandListener.onGuildMessageReceived(event);
        Mockito.verify(action, Mockito.times(1)).queue(Mockito.any());
        Mockito.verify(abstractGuildCommand1, Mockito.never()).processGuildCommand(event);
        Mockito.verify(abstractGuildCommand2, Mockito.never()).processGuildCommand(Mockito.any());
        Mockito.verify(abstractGuildCommand3, Mockito.never()).processGuildCommand(Mockito.any());
        Mockito.verify(abstractPrivateCommand1, Mockito.never()).processPrivateCommand(Mockito.any());
        Mockito.verify(abstractPrivateCommand2, Mockito.never()).processPrivateCommand(Mockito.any());
    }

    @Test
    void onPrivateMessageReceived() {
    }
}