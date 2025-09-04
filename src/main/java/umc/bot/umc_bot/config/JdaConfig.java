package umc.bot.umc_bot.config;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import umc.bot.umc_bot.listener.MessageListener;


@Slf4j
@Configuration
public class JdaConfig {


    @Value("${discord.token}")
    private String token;


    @Bean(destroyMethod = "shutdownNow")
    public JDA jda(MessageListener listener) throws Exception {
        JDA jda = JDABuilder.createDefault(
                        token,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT, // 포털에서 Message Content Intent 활성화 필요
                        GatewayIntent.GUILD_MEMBERS
                )
                .addEventListeners(listener)
                .build();


        jda.awaitReady();
        log.info("✅ JDA ready. Logged in as {}", jda.getSelfUser().getAsTag());
        return jda;
    }
}