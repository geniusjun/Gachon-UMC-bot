package umc.bot.umc_bot.discord.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.springframework.stereotype.Component;

@Component
public class QuoteCommand implements SlashCommand {
    private static final String[] QUOTES = {
            "시도조차 하지 않으면 100% 실패다.",
            "꾸준함은 재능을 이긴다.",
            "완벽보다 완성이 낫다.",
            "작게 시작하되, 크게 생각하라.",
            "하루하루를 마지막이라고 생각하라. \n" +
                    "  그러면 예측할 수 없는 시간은  \n" +
                    "  그대에게 더 많은 시간을 줄 것이다.",
            "꿈을 계속 간직하고 있으면 반드시 실현할 때가 온다.",
            "마음만을 가지고 있어서는 안 된다. 반드시 실천하여야 한다.",
            "오늘이라는 날은 두 번 다시 오지 않는다는 것을 잊지 말라.",


    };

    @Override public String name() { return "quote"; }
    @Override public String description() { return "랜덤 한 줄"; }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        int i = java.util.concurrent.ThreadLocalRandom.current().nextInt(QUOTES.length);
        event.reply("💬 " + QUOTES[i]).queue();
    }
}
