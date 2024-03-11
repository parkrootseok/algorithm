import java.util.HashMap;

/**
 * Programmers_완주하지못한선수
 * @author parkrootseok
 *
 * - 선수
 *  - 완주 못 한 선수는 한 명
 *  - 동명이인이 존재
 *
 * 1. 모든 선수들을 해시맵에 삽입 후 카운트
 * 2. 완주한 선수들이 해시맵에 존재하면 카운트를 감소
 * 3. 모든 선수들의 카운트를 조회
 *  3-1. 카운트가 여전히 남아있다면 해당 선수의 이름을 리턴
 *
 **/

public class Solution {

	public String solution(String[] participant, String[] completion) {


		HashMap<String, Integer> completionPlayers = new HashMap<>();

		// 1. 모든 선수들을 해시맵에 삽입 후 카운트
		for(String player : participant) {
			completionPlayers.put(player, completionPlayers.getOrDefault(player, 0) + 1);
		}

		// 2. 완주한 선수들이 해시맵에 존재하면 카운트를 감소
		for(String player : completion) {
			completionPlayers.put(player, completionPlayers.get(player) - 1);
		}

		// 3. 모든 선수들의 카운트를 조회
		for(String player : participant) {

			// 3-1. 카운트가 여전히 남아있다면 해당 선수의 이름을 리턴
			if (completionPlayers.get(player) >= 1) {
				return player;
			}
		}

		return "";

	}

}