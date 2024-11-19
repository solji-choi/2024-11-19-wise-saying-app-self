package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//명언 만들기 복습하기
public class wiseSaying_self {
    public static void main(String[] args) {
        AppSelf app = new AppSelf();
        app.run();
    }
}

class AppSelf {
    public void run() {
        System.out.println("=== 명언 앱 ===");
        Scanner scanner = new Scanner(System.in);
        List<WiseSayingCon> list = new ArrayList<>();
        int i = 1;

        while(true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            //System.out.println("입력받은 명령어 : %s".formatted(cmd));

            if(cmd.equals("종료")) break;
            else if(cmd.equals("등록")) {
                System.out.print("명언 : ");
                String wiseSaying = scanner.nextLine();

                System.out.print("작가 : ");
                String writer = scanner.nextLine();

                list.add(new WiseSayingCon(i, wiseSaying, writer));

                System.out.println(i + "번 명언이 등록되었습니다.");
                i++;
            } else if(cmd.equals("목록")) {
                for(int j = list.size() - 1; j >= 0; j--){
                    System.out.println(list.get(j).id + " / " + list.get(j).writer + " / " + list.get(j).wiseSaying);
                }
            }
        }
    }
}
class WiseSayingCon {
    int id;
    String wiseSaying;
    String writer;

    WiseSayingCon(int id, String wiseSaying, String writer) {
        this.id = id;
        this.wiseSaying = wiseSaying;
        this.writer = writer;
    }
}