package com.example.mission.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Builder
public class Tag  extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String content;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private final Set<ArticleTagMap> articleSet = new HashSet<>();

    public Tag(String content) {
        this.content = content;
    }
}
