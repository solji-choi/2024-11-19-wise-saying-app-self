package com.ll;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        fileRead(list);

        int id = readLastId();

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            //System.out.println("입력받은 명령어 : %s".formatted(cmd));

            if (cmd.equals("종료")) break;
            else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = scanner.nextLine();

                System.out.print("작가 : ");
                String author = scanner.nextLine();

                list.add(new WiseSaying(id, content, author));
                fileWrite(list, id);
                fileWriteLastId(list, id);

                System.out.println(id + "번 명언이 등록되었습니다.");
                id++;
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");

                if (list.size() > 0) {
                    for (int i = list.size() - 1; i >= 0; i--) {
                        System.out.println("%d / %s / %s".formatted(list.get(i).id, list.get(i).author, list.get(i).content));
                    }
                } else {
                    System.out.println("목록이 없습니다");
                }
            } else if (cmd.startsWith("삭제")) {
                try {
                    execute(cmd, list, "삭제", scanner);
                } catch (WiseSayingException e) {
                    System.out.println(e.getMessage());
                }

            } else if (cmd.startsWith("수정")) {
                try {
                    execute(cmd, list, "수정", scanner);
                } catch (WiseSayingException e) {
                    System.out.println(e.getMessage());
                }

            } else if (cmd.equals("빌드")) {
                actionBuild(list);

            }
        }
    }

    void execute(String cmd, List<WiseSaying> list, String command, Scanner scanner) throws WiseSayingException {
        if (cmd.indexOf("?id=") > -1) {
            int getId = Integer.parseInt(cmd.substring(cmd.indexOf("?id=") + 4));
            boolean listChk = false;

            for (WiseSaying wiseSaying : list) {
                if (wiseSaying.id == getId) {
                    listChk = true;
                }
            }

            if (!listChk) {
                throw new WiseSayingException("%d번 명언은 존재하지 않습니다.".formatted(getId));
            } else {
                if (command.equals("삭제")) {
                    list.removeIf(wiseSaying -> wiseSaying.id == getId);
                    fileDelete(getId);
                    fileWriteLastId(list, getId);

                    System.out.println("%d번 명언이 삭제되었습니다.".formatted(getId));

                } else if (command.equals("수정")) {
                    for (WiseSaying wiseSaying : list) {
                        if (wiseSaying.id == getId) {
                            System.out.println("명언(기존) : %s".formatted(wiseSaying.content));
                            System.out.print("명언 : ");
                            String content2 = scanner.nextLine();
                            wiseSaying.setContent(content2);

                            System.out.println("작가(기존) : %s".formatted(wiseSaying.author));
                            System.out.print("작가 : ");
                            String author2 = scanner.nextLine();
                            wiseSaying.setAuthor(author2);

                            fileWrite(list, getId);
                            fileWriteLastId(list, getId);
                        }
                    }
                }
            }

        } else {
            throw new WiseSayingException("%s할 명언의 id 값을 입력해주세요.".formatted(command));
        }
    }

    void fileWrite(List<WiseSaying> list, int id) {
        FileOutputStream fos = null;
        byte[] fileWriteContent = null;

        for (WiseSaying wiseSaying : list) {
            if (wiseSaying.id == id) {
                fileWriteContent = wiseSaying.toString().getBytes();
            }
        }

        try {
            fos = new FileOutputStream("db/wiseSaying/" + id + ".json");
            fos.write(fileWriteContent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void fileWriteLastId(List<WiseSaying> list, int id) {
        PrintWriter lastFos = null;

        try {
            lastFos = new PrintWriter("db/wiseSaying/lastId.txt");
            lastFos.println(list.get(list.size() - 1).id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                lastFos.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void fileDelete(int id) {
        String filePath = "db/wiseSaying/" + id + ".json";
        Path path = Paths.get(filePath);

        try {
            Files.delete(path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void fileRead(List<WiseSaying> list) {
        BufferedReader fileReader = null;
        File dbDirectory = new File("db/wiseSaying/");
        File[] dbFiles = dbDirectory.listFiles();
        int fileCount = 0;

        if (dbFiles != null) {
            for (File file : dbFiles) {
                if (file.isFile()) {
                    fileCount++;
                }
            }
            try {
                for (int i = 0; i <= fileCount; i++) {
                    for (File file : dbFiles) {
                        if (file.isFile() && file.getName().equals(i + ".json")) {
                            fileReader = new BufferedReader(new FileReader("db/wiseSaying/" + i + ".json"));
                            StringBuffer jsonBuilder = new StringBuffer();
                            String line;

                            while ((line = fileReader.readLine()) != null) {
                                jsonBuilder.append(line);
                            }

                            String jsonString = jsonBuilder.toString();
                            jsonString = jsonString.trim().replace("{", "").replace("}", "").replace("\n", "");
                            String[] attributes = jsonString.split(", ");
                            int id = 0;
                            String content = null;
                            String author = null;

                            for (String attribute : attributes) {
                                String[] keyValue = attribute.split(": ");

                                String key = keyValue[0].trim().replace("\"", "");
                                String value = keyValue[1].trim().replace("\"", "");

                                if (key.equals("id")) {
                                    id = Integer.parseInt(value);
                                } else if (key.equals("content")) {
                                    content = value;
                                } else if (key.equals("author")) {
                                    author = value;
                                }

                                if (id != 0 && content != null && author != null) {
                                    list.add(new WiseSaying(id, content, author));
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    fileReader.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    int readLastId() {
        BufferedReader lastIdReader = null;
        int lastId = 1;

        try {
            lastIdReader = new BufferedReader(new FileReader("db/wiseSaying/lastId.txt"));
            lastId = Integer.parseInt(lastIdReader.readLine()) + 1;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                lastIdReader.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return lastId;
    }

    void actionBuild(List<WiseSaying> list) {
        FileOutputStream fos = null;
        StringBuffer fileWriteContent = new StringBuffer();
        fileWriteContent.append("[\n");
        byte[] fileWriteContents = null;
        for (WiseSaying wiseSaying : list) {
            fileWriteContent.append("  {\n" +
                    "    \"id\": " + wiseSaying.id + ",\n" +
                    "    \"content\": \"" + wiseSaying.content + "\",\n" +
                    "    \"author\": \"" + wiseSaying.author + "\"\n" +
                    "  }").append(",\n");
        }
        fileWriteContent.deleteCharAt(fileWriteContent.length() - 2).append("]");

        fileWriteContents = fileWriteContent.toString().getBytes();

        try {
            fos = new FileOutputStream("db/wiseSaying/data.json");
            fos.write(fileWriteContents);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
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

    void setContent(String content) {
        this.content = content;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"content\": \"" + content + "\",\n" +
                "  \"author\": \"" + author + "\"\n" +
                "}";
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