package com.ll;

public class TVExam {
    public static void main(String[] args) {
        표준Tv a표준Tv;

        a표준Tv = new 샤오미Tv();

        a표준Tv.켜기();
        // 출력 => 샤오미Tv 켜집니다.
        a표준Tv.끄기();
        // 출력 => 샤오미Tv 꺼집니다.

        샤오미Tv a샤오미Tv = (샤오미Tv) a표준Tv;

        a샤오미Tv.vr켜기();
        // 출력 => 샤오미Tv vr켜기!

        a표준Tv = new 삼성Tv();

        a표준Tv.켜기();
        // 출력 => 삼성Tv 켜집니다.
        a표준Tv.끄기();
        // 출력 => 삼성Tv 꺼집니다.

        삼성Tv a삼성Tv = (삼성Tv) a표준Tv;

        a삼성Tv.ar켜기();
        // 출력 => 삼성Tv ar켜기!

        a표준Tv = new LGTv();

        a표준Tv.켜기();
        // 출력 => LGTv 켜집니다.
        a표준Tv.끄기();
        // 출력 => LGTv 꺼집니다.

        LGTv aLGTv = (LGTv) a표준Tv;

        aLGTv.게임모드전환();
        // 출력 => LGTv 게임모드전환!
    }
}

abstract class 표준Tv {
    abstract void 켜기();

    abstract void 끄기();
}

class 샤오미Tv extends 표준Tv {
    void 켜기() {
        System.out.println("샤오미Tv 켜집니다.");
    }

    void 끄기() {
        System.out.println("샤오미Tv 꺼집니다.");
    }

    void vr켜기() {
        System.out.println("샤오미Tv vr켜기!");
    }
}

class 삼성Tv extends 표준Tv {
    void 켜기() {
        System.out.println("삼성Tv 켜집니다.");
    }

    void 끄기() {
        System.out.println("삼성Tv 꺼집니다.");
    }

    void ar켜기() {
        System.out.println("삼성Tv ar켜기!");
    }
}

class LGTv extends 표준Tv {
    void 켜기() {
        System.out.println("LGTv 켜집니다.");
    }

    void 끄기() {
        System.out.println("LGTv 꺼집니다.");
    }

    void 게임모드전환() {
        System.out.println("LGTv 게임모드전환!");
    }
}