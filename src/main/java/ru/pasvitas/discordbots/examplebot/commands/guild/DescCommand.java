package ru.pasvitas.discordbots.examplebot.commands.guild;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;
import ru.pasvitas.discordbots.examplebot.commands.AbstractGuildCommand;
import ru.pasvitas.discordbots.examplebot.commands.BotCommand;
import ru.pasvitas.discordbots.examplebot.service.DictService;

@RequiredArgsConstructor
@BotCommand
public class DescCommand extends AbstractGuildCommand {
    private final DictService dictService; // Зависимости класса

    @Override
    public String getCommandName() { return "как называется"; }

    @Override
    public void processGuildCommand(GuildMessageReceivedEvent message) {
        String wholeMessage = message.getMessage().getContentRaw();
        int wordStartIndex = StringUtils.ordinalIndexOf(wholeMessage, " ", 3) + 1;
        String desc = wholeMessage.substring(wordStartIndex);

        if(desc.contains("?"))
            desc = desc.replaceAll("[?]", "");

        String word = dictService.getWord(desc); //Забираем определение слова

        if (word != null) { //Если слово есть в БД (нет - если таблица пуста)
            message
                    .getChannel() //Получаем канал, куда писать
                    .sendMessage(message.getAuthor().getAsMention() + ", " + desc + " - " + word) //Формируем сообщение (@упоминание, значение)
                    .queue(); //Отправляем
        }
        else { //Если слова не оказалось
            message
                    .getChannel() //Получаем канал, куда писать
                    .sendMessage(message.getAuthor().getAsMention() +  ", иди лучше погугли!") //Формируем сообщение
                    .queue(); //Отправляем
        }
    }
}
