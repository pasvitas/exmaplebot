package ru.pasvitas.discordbots.examplebot.commands.guild;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import ru.pasvitas.discordbots.examplebot.commands.AbstractGuildCommand;
import ru.pasvitas.discordbots.examplebot.commands.BotCommand;
import ru.pasvitas.discordbots.examplebot.service.JokeService;

/**
 * Команда анекдота на сервере
 */
@RequiredArgsConstructor // Вставляет все поля, что помечены final
@BotCommand // Помечает, что класс является командой и втихаря делает пару штук
public class JokeGuildCommand extends AbstractGuildCommand { // Наследуется от команды сервера

    private final JokeService jokeService; // Зависимости класса

    /**
     * Название команды. Именно эта стринга определяет, по какому тексту будет она вызыватся
     * @return название команды
     */
    @Override
    public String getCommandName() {
        return "расскажи анекдот";
    }

    /**
     * Логика команды
     * @param message сообщение из дискорда
     */
    @Override
    public void processGuildCommand(GuildMessageReceivedEvent message) {
        String joke = jokeService.getRandomJoke(); //Забираем рандомную шутку
        if (joke != null) { //Если шутка есть в БД (нет - если таблица пуста)
            message
                    .getChannel() //Получаем канал, куда писать
                    .sendMessage(message.getAuthor().getAsMention() + ", " + joke) //Формируем сообщение (@упоминание, шутка)
                    .queue(); //Отправляем
        }
        else { //Если шутки не оказалось
            message
                    .getChannel() //Получаем канал, куда писать
                    .sendMessage(message.getAuthor().getAsMention() +  ", я не знаю никаких анекдотов!") //Формируем сообщение
                    .queue(); //Отправляем
        }
    }
}
