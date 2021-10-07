package ru.pasvitas.discordbots.examplebot.commands.guild;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;
import ru.pasvitas.discordbots.examplebot.commands.AbstractGuildCommand;
import ru.pasvitas.discordbots.examplebot.commands.BotCommand;
import ru.pasvitas.discordbots.examplebot.service.DictService;

@RequiredArgsConstructor
@BotCommand
public class DictCommand extends AbstractGuildCommand {

    private final DictService dictService; // Зависимости класса

    @Override
    public String getCommandName() {
        return "что такое";
    }

    @Override
    public void processGuildCommand(GuildMessageReceivedEvent message) {
        String wholeMessage = message.getMessage().getContentRaw();
        int wordStartIndex = StringUtils.ordinalIndexOf(wholeMessage, " ", 3) + 1;
        String word = wholeMessage.substring(wordStartIndex);

        if (word.isBlank()) {
            message
                    .getChannel() //Получаем канал, куда писать
                    .sendMessage(message.getAuthor().getAsMention() + ", чего бл? Повтори нормально!") //Формируем сообщение (@упоминание, значение)
                    .queue(); //Отправляем
        }

        if (word.contains("?")) {
            word = word.replaceAll("[?]", "");
        }

        String description = dictService.getDescription(word); //Забираем определение слова

        if (description != null) { //Если слово есть в БД (нет - если таблица пуста)
            message
                    .getChannel() //Получаем канал, куда писать
                    .sendMessage(message.getAuthor().getAsMention() + ", " + word + " - " + description) //Формируем сообщение (@упоминание, значение)
                    .queue(); //Отправляем
        } else { //Если слова не оказалось
            message
                    .getChannel() //Получаем канал, куда писать
                    .sendMessage(message.getAuthor().getAsMention() + ", я таких слов не знаю!") //Формируем сообщение
                    .queue(); //Отправляем
        }
    }
}
