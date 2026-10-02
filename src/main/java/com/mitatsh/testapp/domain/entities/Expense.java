package com.mitatsh.testapp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="expenses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Expense {

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
    private LocalDateTime spentAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Expense expense = (Expense) o;
        return Float.compare(amount, expense.amount) == 0 && Objects.equals(id, expense.id)
                && Objects.equals(name, expense.name) && Objects.equals(category, expense.category)
                && Objects.equals(createdAt, expense.createdAt) && Objects.equals(spentAt, expense.spentAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Float.hashCode(amount);
        result = 31 * result + Objects.hashCode(category);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(spentAt);
        return result;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    protected void setSpentAt(LocalDateTime spentAt) {
        this.spentAt = spentAt;
    }
}
