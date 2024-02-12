package study.datajpa.repository;

/**
 * 중첩 조회
 */
public interface NestedClosedProjections {
    String getUsername();

    interface TeamInfo {
        String getName();
    }

}
