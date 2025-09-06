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
        var cmd = slash(name(), description());
        for (int i = 1; i <= 10; i++) {
            cmd.addOption(OptionType.STRING, "opt" + i, "선택지 " + i, i <= 2); // 최소 2개 필수
        }
        return cmd;
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        List<String> opts = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            if (event.getOption("opt" + i) != null) {
                opts.add(event.getOption("opt" + i).getAsString());
            }
        }
        if (opts.size() < 2) {
            event.reply("❗ 최소 두 개 이상 입력해줘!").setEphemeral(true).queue();
            return;
        }
        String pick = opts.get(ThreadLocalRandom.current().nextInt(opts.size()));
        event.reply("🤔 제가 고른 건... **" + pick + "** !").queue();
    }
}
