package com.koowin.chesswithreview.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class GameResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @OneToOne
    private Member white;

    @Column
    @OneToOne
    private Member black;


    @Column
    private boolean isWhiteWin;

    @Builder
    public GameResult(Member white, Member black, boolean isWhiteWin) {
        this.white = white;
        this.black = black;
        this.isWhiteWin = isWhiteWin;
    }
}

