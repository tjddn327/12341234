package nhn.academy.controller;

import nhn.academy.model.ClassType;
import nhn.academy.model.Member;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CsvHttpMessageConverter extends AbstractHttpMessageConverter<Member> {

    public CsvHttpMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    protected Member readInternal(Class<? extends Member> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        //TODO Not Supported Type
        return null;
    }

    @Override
    protected boolean canRead(MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        boolean b = super.canWrite(clazz, mediaType);
        return b;
    }

    @Override
    protected void writeInternal(Member member
            , HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        outputMessage.getHeaders().setContentType(MediaType.valueOf("text/csv; charset=UTF-8"));
        try (Writer writer = new OutputStreamWriter(outputMessage.getBody())) {
            //TODO
            writer.write("name,age,class\n");

            writer.write(String.format("%s,%d,%s\n",
                    member.getName(),
                    member.getAge(),
                    member.getClazz().name()));
            writer.flush();
        }
    }




}