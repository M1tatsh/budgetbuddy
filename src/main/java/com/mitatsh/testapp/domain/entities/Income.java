package com.mitatsh.testapp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="income")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime earnedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Income income = (Income) o;
        return Float.compare(amount, income.amount) == 0 && Objects.equals(id, income.id)
                && Objects.equals(name, income.name) && Objects.equals(category, income.category)
                && Objects.equals(createdAt, income.createdAt) && Objects.equals(earnedAt, income.earnedAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Float.hashCode(amount);
        result = 31 * result + Objects.hashCode(category);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(earnedAt);
        return result;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    protected void setEarnedAt(LocalDateTime earnedAt) {
        this.earnedAt = earnedAt;
    }
}
