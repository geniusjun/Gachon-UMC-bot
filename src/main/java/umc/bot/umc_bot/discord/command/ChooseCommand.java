package umc.bot.umc_bot.discord.command;


import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static net.dv8tion.jda.api.interactions.commands.build.Commands.slash;

@Component
public class ChooseCommand implements SlashCommand {
    @Override public String name() { return "choose"; }
    @Override public String description() { return "여러 선택지 중 하나를 랜덤 선택"; }

    @Override
    public CommandData commandData() {
        return slash(name(), description())
                .addOption(OptionType.STRING, "a", "선택지 A", true)
                .addOption(OptionType.STRING, "b", "선택지 B", true)
                .addOption(OptionType.STRING, "c", "선택지 C", false)
                .addOption(OptionType.STRING, "d", "선택지 D", false)
                .addOption(OptionType.STRING, "e", "선택지 E", false);
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        List<String> opts = new ArrayList<>();
        for (String k : List.of("a","b","c","d","e")) {
            if (event.getOption(k) != null) opts.add(event.getOption(k).getAsString());
        }
        if (opts.size() < 2) {
            event.reply("최소 두 개 이상 입력해줘!").setEphemeral(true).queue();
            return;
        }
        String pick = opts.get(ThreadLocalRandom.current().nextInt(opts.size()));
        event.reply("🤔 제가 고른 건... **" + pick + "** !").queue();
    }
}
