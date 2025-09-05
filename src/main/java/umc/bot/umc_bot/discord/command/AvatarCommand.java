package umc.bot.umc_bot.discord.command;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.springframework.stereotype.Component;

import static net.dv8tion.jda.api.interactions.commands.build.Commands.slash;

@Component
public class AvatarCommand implements SlashCommand {
    @Override public String name() { return "avatar"; }
    @Override public String description() { return "내(또는 지정한 유저) 아바타 보기"; }

    @Override
    public CommandData commandData() {
        return slash(name(), description())
                .addOption(OptionType.USER, "user", "대상 유저", false);
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        User target = event.getOption("user") != null ? event.getOption("user").getAsUser() : event.getUser();
        String url = target.getEffectiveAvatarUrl() + "?size=512";
        event.reply("🖼️ " + target.getAsTag() + "의 아바타\n" + url).queue();
    }
}
