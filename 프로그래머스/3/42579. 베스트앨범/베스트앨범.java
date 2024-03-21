import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/**
 * Programmers_베스트앨범
 *
 * 베스트 앨범
 *  - 장르별로 가장 많이 재생된 2곡을 수록
 *  - 곡을 수록할 때 가장 많이 재생된 장르부터 수록됨
 *
 *  1. 각 장르별 총 재생횟수를 카운팅하고, TreeSet에 음악 객체를 보관
 *  2. 장르를 총 재생횟수를 기준으로 내림차순 정렬
 *  3. 정렬한 장르를 기준으로 TreeSet에서 우선순위가 가장 높은 음악의 고유번호를 추출
 */
public class Solution {

	public static void main(String[] args) {
		Solution s = new Solution();
		s.solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500});
	}

	class Music implements Comparable<Music> {

		int idx;
		int plays;

		public Music(int idx, int plays) {
			this.idx = idx;
			this.plays = plays;
		}

		@Override
		public int compareTo(Music o) {

			if(this.plays == o.plays) {
				return Integer.compare(this.idx, o.idx);
			}

			return Integer.compare(o.plays, this.plays);

		}

	}

	public int[] solution(String[] genres, int[] plays) {

		// 1. 각 장르별 총 재생횟수를 카운팅 및 set에 음악 객체를 담는다.
		HashMap<String, Integer> totalPlay = new HashMap<>();
		HashMap<String, TreeSet<Music>> musicsMap = new HashMap<>();
		for (int idx = 0; idx < genres.length; idx++) {

			// 재생횟수 카운팅
			totalPlay.put(genres[idx], totalPlay.getOrDefault(genres[idx], 0) + plays[idx]);

			// 장르별 treeSet에 Music 객체를 추가
			// 이때 정렬 기준은 재생횟수 내림차순이고 같다면 고유번호 오름차순
			if (musicsMap.get(genres[idx]) == null) {
				musicsMap.put(genres[idx], new TreeSet<>());
				musicsMap.get(genres[idx]).add(new Music(idx, plays[idx]));;
			}

			else {
				musicsMap.get(genres[idx]).add(new Music(idx, plays[idx]));
			}

		}

		// 2. 장르를 총 재생횟수를 기준으로 내림차순 정렬
		List<String> SortedTotalPlay = new ArrayList<>(totalPlay.keySet());
		Collections.sort(SortedTotalPlay, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return totalPlay.get(o2) - totalPlay.get(o1);
			}
		});

		// 3. 정렬한 장르를 기준으로 TreeSet에서 우선순위가 가장 높은 음악의 고유번호를 추출
		List<Integer> idxList = new ArrayList<>();
		for (String genre : SortedTotalPlay) {

			TreeSet<Music> musicsSet =  musicsMap.get(genre);

			// 가장 우선순위가 높은 음악의 고유번호 추출
			idxList.add(musicsSet.pollFirst().idx);

			// 만약, 곡이 1개가 아니라면 총 2곡을 수록
			if(musicsSet.size() >= 1) {
				idxList.add(musicsSet.pollFirst().idx);
			}

		}
		
		int[] answer = new int[idxList.size()];
		for(int idx = 0; idx < idxList.size(); idx++) {
			answer[idx] = idxList.get(idx);
		}

		return answer;

	}

}