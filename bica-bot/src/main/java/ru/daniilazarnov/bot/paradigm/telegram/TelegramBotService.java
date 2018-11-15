package ru.daniilazarnov.bot.paradigm.telegram;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.daniilazarnov.bot.paradigm.profile.ProfileParadigm;
import ru.daniilazarnov.bot.transport.dto.EventTO;
import ru.daniilazarnov.bot.paradigm.BotService;

@Service
@Profile(ProfileParadigm.TELEGRAM)
public class TelegramBotService implements BotService {
    @Override
    public EventTO eventHandle(EventTO eventTO) {
        return new EventTO();
    }
}
