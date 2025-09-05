package umc.bot.umc_bot.discord.command;


import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.springframework.stereotype.Component;

@Component
public class SayCommand implements SlashCommand {
    @Override public String name() { return "say"; }
    @Override public String description() { return "말말말"; }
    @Override public void handle(SlashCommandInteractionEvent event) {
        var opt = event.getOption("UMC 9기 파이팅~~");
        event.reply(opt == null ? "(no text)" : opt.getAsString()).queue();
    }
}