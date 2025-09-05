package umc.bot.umc_bot.domain.announcement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, String> {
    List<AnnouncementEntity> findBySentFalseAndScheduleAtLessThanEqual(LocalDateTime now);
    List<AnnouncementEntity> findByGuildIdOrderByCreatedAtDesc(String guildId);
}