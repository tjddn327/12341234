package nhn.academy.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class JacksonConfig {
    public static class IntegerSerializer extends StdSerializer<Integer> {
        private static final long serialVersionUID = -7524016618355224119L;

        public IntegerSerializer(){
            super(Integer.class);
        }

        protected IntegerSerializer(Class<Integer> t) {
            super(t);
        }

        protected IntegerSerializer(JavaType type) {
            super(type);
        }

        protected IntegerSerializer(Class<?> t, boolean dummy) {
            super(t, dummy);
        }

        protected IntegerSerializer(StdSerializer<?> src) {
            super(src);
        }

        @Override
        public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeNumber(integer.toString());
        }
    }
}
