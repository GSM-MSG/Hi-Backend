package msg.team1.Hi.domain.member.enum_type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UsingStatus {
    AVAILABLE, FORBIDDEN, INUSE;

    @JsonCreator
    public static UsingStatus from(String s) {
        return UsingStatus.valueOf(s.toUpperCase());
    }
}
