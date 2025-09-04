package umc.bot.umc_bot.discord;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.springframework.stereotype.Component;
import umc.bot.umc_bot.discord.command.SlashCommand;

import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommandDispatcher {
    private final java.util.List<SlashCommand> commands;

    public void dispatch(SlashCommandInteractionEvent event) {
        Map<String, SlashCommand> map = commands.stream()
                .collect(Collectors.toMap(SlashCommand::name, c -> c));
        SlashCommand cmd = map.get(event.getName());
        if (cmd != null) cmd.handle(event);
        else event.reply("Unknown command").setEphemeral(true).queue();
    }
}
