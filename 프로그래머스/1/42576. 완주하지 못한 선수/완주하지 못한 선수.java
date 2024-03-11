import java.util.HashMap;

/**
 * Programmers_완주하지못한선수
 * @author parkrootseok
 *
 * - 선수
 *  - 완주 못 한 선수는 한 명
 *  - 동명이인이 존재
 *
 * 1. 완주한 선수들을 해시맵에 삽입 후 카운트
 *
 **/

public class Solution {

	public String solution(String[] participant, String[] completion) {

		HashMap<String, Integer> completionPlayers = new HashMap<>();
		for(String player : participant) {
			completionPlayers.put(player, completionPlayers.getOrDefault(player, 0) + 1);
		}

		for(String player : completion) {
			completionPlayers.put(player, completionPlayers.get(player) - 1);
		}

		for(String player : participant) {
			if (completionPlayers.get(player) >= 1) {
				return player;
			}
		}

		return "";

	}

}