package umc.bot.umc_bot.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class BaseEntity {

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        var now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.onPrePersistChild();   // 자식 훅
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.onPreUpdateChild();    // 자식 훅
    }

    // 자식에서 필요한 경우만 오버라이드
    protected void onPrePersistChild() {}
    protected void onPreUpdateChild() {}
}