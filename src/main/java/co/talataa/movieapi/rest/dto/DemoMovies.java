package co.talataa.movieapi.rest.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DemoMovies {
    public static List<Integer> loadDemoList() {
        return List.of(76600, 436270, 438148, 505642, 585511, 675353, 724495, 736526, 747803, 760161, 766507, 774752, 792775, 795514, 829799,
                830788, 846778, 852046, 855440, 856245, 872177, 873126, 882598, 888838, 899112, 916605, 928391, 934641, 941520, 948276, 960096,
                960704, 966220, 972313, 1013860, 1024546, 1030419, 1040603, 1045944, 1049233);
    }
}
