package umc.bot.umc_bot.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import umc.bot.umc_bot.discord.CommandDispatcher;


@Slf4j
@Component
@RequiredArgsConstructor
public class MessageListener extends ListenerAdapter {
    private final CommandDispatcher dispatcher;

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        dispatcher.dispatch(event);
    }
}