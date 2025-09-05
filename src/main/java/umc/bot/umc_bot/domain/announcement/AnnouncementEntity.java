package umc.bot.umc_bot.domain.announcement;

import jakarta.persistence.*;
import lombok.*;
import umc.bot.umc_bot.domain.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "announcement",
        indexes = {
                @Index(name = "idx_announcement_sent_schedule", columnList = "sent, schedule_at"),
                @Index(name = "idx_announcement_guild_channel", columnList = "guild_id, channel_id")
        })
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class AnnouncementEntity extends BaseEntity {

    @Id
    @Column(length = 36, nullable = false)
    private String id;  // UUID 문자열

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private AnnouncementStyle style; // PLAIN / EMBED

    @Column(name = "guild_id", nullable = false, length = 20)
    private String guildId;

    @Column(name = "channel_id", nullable = false, length = 20)
    private String channelId;

    @Column(name = "author_id", nullable = false, length = 20)
    private String authorId;

    @Column(length = 200)
    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String text; // 본문(길게)

    @Column(name = "color_rgb")
    private Integer colorRgb; // 0xRRGGBB (null 허용)

    @Column(name = "mention_role_id", length = 20)
    private String mentionRoleId;

    @Column(name = "mention_everyone", nullable = false)
    private boolean mentionEveryone;

    @Column(name = "schedule_at")
    private LocalDateTime scheduleAt;  // 예약 시간 (KST 기준으로 해석)

    @Column(name = "sent", nullable = false)
    private boolean sent;

    @Override
    protected void onPrePersistChild() {
        if (this.id == null) this.id = UUID.randomUUID().toString();
        if (this.style == null) this.style = AnnouncementStyle.PLAIN;
        // createdAt/updatedAt은 건드리지 않음 (BaseEntity가 처리)
    }

    @Override
    public void onPreUpdateChild() {
        // updatedAt은 건드리지 않음 (BaseEntity가 처리)
    }
}
