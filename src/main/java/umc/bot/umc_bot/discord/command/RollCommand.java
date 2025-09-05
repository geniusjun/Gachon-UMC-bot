package umc.bot.umc_bot.discord.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.springframework.stereotype.Component;

import static net.dv8tion.jda.api.interactions.commands.build.Commands.slash;

@Component
public class RollCommand implements SlashCommand {
    @Override public String name() { return "roll"; }
    @Override public String description() { return "주사위 굴리기 (기본 6면)"; }

    @Override
    public CommandData commandData() {
        return slash(name(), description())
                .addOption(OptionType.INTEGER, "sides", "면의 수 (2~1000)", false);
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        int sides = event.getOption("sides") != null ? event.getOption("sides").getAsInt() : 6;
        if (sides < 2 || sides > 1000) {
            event.reply("면 수는 2~1000 사이로 주세요!").setEphemeral(true).queue();
            return;
        }
        int v = 1 + java.util.concurrent.ThreadLocalRandom.current().nextInt(sides);
        event.reply("🎲 **" + v + "** (1-" + sides + ")").queue();
    }
}
