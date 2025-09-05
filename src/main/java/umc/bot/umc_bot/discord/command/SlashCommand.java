package umc.bot.umc_bot.discord.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public interface SlashCommand {
    String name();
    String description();
    void handle(SlashCommandInteractionEvent event);

    default CommandData commandData() {
        return Commands.slash(name(), description()); // 옵션 없는 기본형
    }
}
