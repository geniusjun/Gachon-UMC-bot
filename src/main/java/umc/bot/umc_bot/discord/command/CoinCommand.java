package umc.bot.umc_bot.discord.command;


import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.springframework.stereotype.Component;

@Component
public class CoinCommand implements SlashCommand {
    @Override public String name() { return "coin"; }
    @Override public String description() { return "동전 던지기 (앞/뒤)"; }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        String res = java.util.concurrent.ThreadLocalRandom.current().nextBoolean() ? "앞(Heads)" : "뒤(Tails)";
        event.reply("🪙 결과: **" + res + "**").queue();
    }
}
