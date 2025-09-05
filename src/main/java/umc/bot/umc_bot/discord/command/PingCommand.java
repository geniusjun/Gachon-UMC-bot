package umc.bot.umc_bot.discord.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.springframework.stereotype.Component;

@Component
public class PingCommand implements SlashCommand {
    @Override public String name() { return "ping"; }
    @Override public String description() { return "pong을 보냅니다"; }
    @Override public void handle(SlashCommandInteractionEvent event) {
        event.reply("🏓 pong!").setEphemeral(true).queue();
    }
}