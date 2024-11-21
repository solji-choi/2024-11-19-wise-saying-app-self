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
        List<WiseSaying> list = new ArrayList<>();
        int id = 1;

        while(true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            //System.out.println("입력받은 명령어 : %s".formatted(cmd));

            if(cmd.equals("종료")) break;
            else if(cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = scanner.nextLine();

                System.out.print("작가 : ");
                String author = scanner.nextLine();

                list.add(new WiseSaying(id, content, author));

                System.out.println(id + "번 명언이 등록되었습니다.");
                id++;
            } else if(cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for(int i = list.size() - 1; i >= 0; i--){
                    System.out.println("%d / %s / %s".formatted(list.get(i).id, list.get(i).author, list.get(i).content));
                }
            } else if(cmd.startsWith("삭제")) {
                try {
                    delete(cmd, list);
                } catch (WiseSayingException e) {
                    System.out.println(e.getMessage());
                }

            }
        }
    }

    void delete(String cmd, List<WiseSaying> list) throws WiseSayingException {
        if(cmd.indexOf("?id=") > -1) {
            int getId =  Integer.parseInt(cmd.substring(cmd.indexOf("?id=") + 4));
            boolean listChk = false;

            for(WiseSaying wiseSaying : list) {
                if (wiseSaying.id == getId) {
                    listChk = true;
                }
            }

            if(!listChk) {
                throw new WiseSayingException("%d번 명언은 존재하지 않습니다.".formatted(getId));
            } else {
                list.removeIf(wiseSaying -> wiseSaying.id == getId);
                System.out.println("%d번 명언이 삭제되었습니다.".formatted(getId));
            }

        } else {
            throw new WiseSayingException("삭제할 명언의 id 값을 입력해주세요.");
        }
    }
}
class WiseSaying {
    int id;
    String content;
    String author;

    WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}

class WiseSayingException extends RuntimeException {
    WiseSayingException(String msg) {
        super(msg);
    }
    WiseSayingException(Exception ex) {
        super(ex);
    }
}