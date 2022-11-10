package msg.team1.Hi.global.role.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import msg.team1.Hi.global.role.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleTypeConverter implements Converter<String, Role> {

    @Override
    public Role convert(String value) {
        return Role.valueOf(value.toUpperCase());
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }

}
