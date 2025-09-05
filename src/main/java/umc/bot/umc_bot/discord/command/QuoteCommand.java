package umc.bot.umc_bot.discord.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.springframework.stereotype.Component;

@Component
public class QuoteCommand implements SlashCommand {
    private static final String[] QUOTES = {
            "시도조차 하지 않으면 100% 실패다.",
            "꾸준함은 재능을 이긴다.",
            "완벽보다 완성이 낫다.",
            "작게 시작하되, 크게 생각하라."
    };

    @Override public String name() { return "quote"; }
    @Override public String description() { return "랜덤 한 줄"; }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        int i = java.util.concurrent.ThreadLocalRandom.current().nextInt(QUOTES.length);
        event.reply("💬 " + QUOTES[i]).queue();
    }
}
