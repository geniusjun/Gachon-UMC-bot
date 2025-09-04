package umc.bot.umc_bot.listener;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MessageListener extends ListenerAdapter {


    @Value("${discord.guild-id}")
    private String guildId;


    // 봇 로그인 완료 시 슬래시 커맨드 등록
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        if (guildId != null && !guildId.isBlank()) {
            Guild guild = event.getJDA().getGuildById(guildId);
            if (guild != null) {
                guild.updateCommands().addCommands(
                        Commands.slash("ping", "pong을 보냅니다"),
                        Commands.slash("say", "에코").addOption(OptionType.STRING, "text", "보낼 말", true)
                ).queue(s -> log.info("✅ Guild commands registered in {}", guild.getName()),
                        e -> log.error("Failed to register commands", e));
            }
        } else {
            // 길드ID가 없으면 글로벌 등록 (전파까지 수 분~1시간 가능)
            event.getJDA().updateCommands().addCommands(
                    Commands.slash("ping", "pong을 보냅니다"),
                    Commands.slash("say", "에코").addOption(OptionType.STRING, "text", "보낼 말", true)
            ).queue();
            log.warn("No guild-id configured. Registered as GLOBAL commands (may take time)");
        }
    }


    // 구형 텍스트 명령: !ping
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String raw = event.getMessage().getContentRaw().trim();
        if (raw.equalsIgnoreCase("!ping")) {
            event.getMessage().reply("🏓 pong!").queue();
        }
    }


    // 슬래시 커맨드 응답
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "ping" -> event.reply("🏓 pong!").queue();
            case "say" -> event.reply(event.getOption("text").getAsString()).queue();
            default -> event.reply("Unknown command").setEphemeral(true).queue();
        }
    }
}