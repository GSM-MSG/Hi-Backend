package msg.team1.Hi.domain.member.enum_type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    AVAILABLE, FORBIDDEN, INUSE;

    @JsonCreator
    public static Status from(String s) {
        return Status.valueOf(s.toUpperCase());
    }
}
