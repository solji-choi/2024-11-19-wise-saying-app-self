package com.ll.domain.wiseSaying;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    App app = new App();

    public void actionAdd(Scanner scanner, List<WiseSaying> wiseSayingList, int lastId) {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String author = scanner.nextLine();

        wiseSayingList.add(new WiseSaying(lastId, content, author));
        app.fileWrite(wiseSayingList, lastId);
        app.fileWriteLastId(wiseSayingList, lastId);

        System.out.println(lastId + "번 명언이 등록되었습니다.");
        lastId++;
    }

    public void actionList(List<WiseSaying> wiseSayingList) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        if (wiseSayingList.size() > 0) {
            for (int i = wiseSayingList.size() - 1; i >= 0; i--) {
                System.out.println("%d / %s / %s".formatted(wiseSayingList.get(i).id, wiseSayingList.get(i).author, wiseSayingList.get(i).content));
            }
        } else {
            System.out.println("목록이 없습니다");
        }
    }
}
