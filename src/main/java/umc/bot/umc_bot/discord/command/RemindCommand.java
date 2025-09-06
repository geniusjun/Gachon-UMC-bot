package umc.bot.umc_bot.discord.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.dv8tion.jda.api.interactions.commands.build.Commands.slash;

@Component
public class RemindCommand implements SlashCommand {
    @Override public String name() { return "remind"; }
    @Override public String description() { return "특정 시간 후 알림 보내기"; }

    @Override
    public CommandData commandData() {
        return slash(name(), description())
                .addOption(OptionType.STRING, "time", "알림까지 시간 (예: 10s, 5m, 2h)", true)
                .addOption(OptionType.STRING, "text", "알림 내용", true);
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        String timeStr = event.getOption("time").getAsString();
        String text = event.getOption("text").getAsString();

        long delayMillis = parseTime(timeStr);
        if (delayMillis <= 0) {
            event.reply("시간 형식이 잘못됐어! (예: 10s, 5m, 2h)").setEphemeral(true).queue();
            return;
        }

        event.reply("⏰ 알림 예약 완료! " + timeStr + " 뒤에 알려줄게요.").queue();

        // 일정 시간 뒤에 실행
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            event.getUser().openPrivateChannel().queue(channel -> {
                channel.sendMessage("🔔 리마인드: " + text).queue();
            });
        }, delayMillis, TimeUnit.MILLISECONDS);
    }

    private long parseTime(String timeStr) {
        try {
            if (timeStr.endsWith("s")) return Long.parseLong(timeStr.replace("s","")) * 1000;
            if (timeStr.endsWith("m")) return Long.parseLong(timeStr.replace("m","")) * 60 * 1000;
            if (timeStr.endsWith("h")) return Long.parseLong(timeStr.replace("h","")) * 60 * 60 * 1000;
        } catch (NumberFormatException e) {
            return -1;
        }
        return -1;
    }
}
