package programmers.openchatting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 2019 KAKAO BLIND RECRUIT
 */
public class Solution {

    private static final String ENTER_FORMAT = "%s님이 들어왔습니다.";
    private static final String LEAVE_FORMAT = "%s님이 나갔습니다.";

    private class User {

        public String userId;
        public String nickName;

        public User(String userId, String nickName) {
            this.userId = userId;
            this.nickName = nickName;
        }

    }

    private class Action {

        public String command;
        public String userId;

        public Action(String command, String userId) {
            this.command = command;
            this.userId = userId;
        }

    }

    public String[] solution(String[] record) {

        ArrayList<Action> actions = new ArrayList<>();
        HashMap<String, User> users = new HashMap<>();

        for (String r: record) {

            String[] info = r.split(" ");
            String cmd = info[0];
            String uId = info[1];
            String nickName = null;

            switch (info[0]) {

                case "Enter":

                    if (!users.containsKey(uId)) {
                        users.put(info[1], new User(uId, info[2]));
                    } else {
                        users.get(uId).nickName = info[2];
                    }

                    actions.add(new Action(cmd, uId));
                    break;

                case "Leave":
                    actions.add(new Action(cmd, uId));
                    break;

                case "Change":
                    users.get(uId).nickName = info[2];
                    break;

            }

        }

        System.out.println("");

        return actions.stream()
                .map(act -> String.format(act.command.equals("Enter") ? ENTER_FORMAT : LEAVE_FORMAT, users.get(act.userId).nickName))
                .toArray(array -> new String[actions.size()]);

    }

    public static void main(String[] args) {

        Solution s = new Solution();
        Scanner sc = new Scanner(System.in);

        String[] record = {
                "Enter uid1234 Muzi",
                "Enter uid4567 Prodo",
                "Leave uid1234",
                "Enter uid1234 Prodo",
                "Change uid4567 Ryan"
        };

        String[] result =  s.solution(record);
        for (String r : result) {
            System.out.println(r);
        }

    }
}
