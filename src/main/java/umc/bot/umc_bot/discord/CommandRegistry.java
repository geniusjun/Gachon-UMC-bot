package umc.bot.umc_bot.discord;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import umc.bot.umc_bot.discord.command.SlashCommand;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandRegistry {
    private final JDA jda;
    private final List<SlashCommand> commands;

    @Value("${discord.guild-id:}")
    private String guildId;

    @PostConstruct
    public void register() {
        List<CommandData> datas = commands.stream()
                .map(SlashCommand::commandData)
                .collect(java.util.stream.Collectors.toList());


        if (guildId != null && !guildId.isBlank() && jda.getGuildById(guildId) != null) {
            jda.getGuildById(guildId).updateCommands().addCommands(datas).queue(
                    ok -> log.info("✅ Registered {} guild commands", datas.size()),
                    err -> log.error("Failed to register guild commands", err));
        } else {
            jda.updateCommands().addCommands(datas).queue(
                    ok -> log.info("✅ Registered {} global commands", datas.size()),
                    err -> log.error("Failed to register global commands", err));
        }
    }
}